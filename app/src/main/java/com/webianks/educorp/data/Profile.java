package com.webianks.educorp.data;

import java.util.List;

/**
 * Created by R Ankit on 14-01-2017.
 */

public class Profile {


    private List<ProfileBean> profile;

    public List<ProfileBean> getProfile() {
        return profile;
    }

    public void setProfile(List<ProfileBean> profile) {
        this.profile = profile;
    }

    public static class ProfileBean {

        /**
         * id : 79
         * name : Ramankit Singh
         * email : salroid@gmail.com
         * account_type : Tutor
         * profile : null
         */

        private String id;
        private String name;
        private String email;
        private String account_type;
        private Object profile;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAccount_type() {
            return account_type;
        }

        public void setAccount_type(String account_type) {
            this.account_type = account_type;
        }

        public Object getProfile() {
            return profile;
        }

        public void setProfile(Object profile) {
            this.profile = profile;
        }
    }
}
