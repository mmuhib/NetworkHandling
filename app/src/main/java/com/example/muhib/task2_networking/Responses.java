package com.example.muhib.task2_networking;

import java.util.List;

public class Responses {
    /**
     * status : true
     * response : {"language_list":[{"language_id":"2","language_code":"ar","language_name":"Arabic"},{"language_id":"1","language_code":"en","language_name":"English"}]}
     */

    private boolean status;
    private ResponseBean response;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public static class ResponseBean {
        private List<LanguageListBean> language_list;

        public List<LanguageListBean> getLanguage_list() {
            return language_list;
        }

        public void setLanguage_list(List<LanguageListBean> language_list) {
            this.language_list = language_list;
        }

        public static class LanguageListBean {
            /**
             * language_id : 2
             * language_code : ar
             * language_name : Arabic
             */

            private String language_id;
            private String language_code;
            private String language_name;
            public String getLanguage_id() {
                return language_id;
            }

            public void setLanguage_id(String language_id) {
                this.language_id = language_id;
            }

            public String getLanguage_code() {
                return language_code;
            }

            public void setLanguage_code(String language_code) {
                this.language_code = language_code;
            }
            public String getLanguage_name() {
                return language_name;
            }

            public void setLanguage_name(String language_name) {
                this.language_name = language_name;
            }
        }
    }
}
