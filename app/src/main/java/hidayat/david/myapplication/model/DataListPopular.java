package hidayat.david.myapplication.model;

import java.util.ArrayList;

public class DataListPopular {
    private int page,total_results,total_pages;
    private ArrayList<DataPopular> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public void setResults(ArrayList<DataPopular> results) {
        this.results = results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public ArrayList<DataPopular> getResults() {
        return results;
    }

}
