package com.example.theguardiannewsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

        @SerializedName("results")
        @Expose
        private List<Result> results;

        @SerializedName("status")
        @Expose
        private String status;

//        @SerializedName("currentPage")
//        @Expose
//        private int currentPage;

        public Response() {}

        public List<Result> getResults() {
                return results;
        }

        public void setResults(List<Result> results) {
                this.results = results;
        }

        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }

//        public int getCurrentPage() {
//                return currentPage;
//        }
//
//        public void setCurrentPage(int currentPage) {
//                this.currentPage = currentPage;
//        }
}


