package org.docksidestage.javatry.basic.st6.dbms;

abstract class St6QueryBuilder {

    public String buildPagingQuery(int pageSize, int pageNumber) {
        int offset = pageSize * (pageNumber - 1);
        return generateQuery(offset, pageSize);
    }

    abstract String generateQuery(int offset, int pageSize);
}
