package com.xtremedreamers.webuy.shared;

import java.util.ArrayList;
import java.util.List;

import com.xtremedreamers.webuy.persistence.GenericDao;

public class PagedList<T> extends ArrayList<T> {
    public MetaData metaData;

    public PagedList(List<T> items, int count, int pageNumber, int pageSize) {
        MetaData metaData = new MetaData(pageNumber, (int) Math.ceil(count / (double) pageSize), pageSize, count);
        this.metaData = metaData;
        addAll(items);
    }

    public static <T> PagedList<T> toPagedList(GenericDao<T, Integer> dao, int pageNumber, int pageSize) {
        // contar cuantas entidades, y mandar el query de paginacion
        int count = dao.count(); // unico lugar donde se usa count
        var items = dao.getPagination(pageNumber, pageSize);
        return new PagedList<T>(items, count, pageNumber, pageSize);
    }
    

    public static <T> PagedList<T> toPagedList(GenericDao<T, Integer> dao, int pageNumber, int pageSize, String sortByName) {
    	int count = dao.count();
    	var items = dao.getPagination(pageNumber, pageSize, sortByName);
    	return new PagedList<T>(items, count, pageNumber, pageSize);

    public static <T> PagedList<T> toPagedList(List<T> items){
    	return new PagedList<T>(items, items.size(), 1, 8);
    }

    public MetaData getMetaData() {
        return metaData;
    }

}
