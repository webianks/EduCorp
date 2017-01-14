package com.webianks.educorp.model;

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
        private List<CoreProfileBean> profile;

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

        public List<CoreProfileBean> getProfile() {
            return profile;
        }

        public void setProfile(List<CoreProfileBean> profile) {
            this.profile = profile;
        }
    }

    public static class CoreProfileBean {

        private String address;
        private String profile_pic_url;
        private String zipcode;
        private String bio;
        private  List<String> students;
        private String student_name;
        private String student_school;
        private String student_grade;
        private List<String> subjects;

        public void setAddress(String address) {
            this.address = address;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public void setProfile_pic_url(String profile_pic_url) {
            this.profile_pic_url = profile_pic_url;
        }

        public void setStudent_grade(String student_grade) {
            this.student_grade = student_grade;
        }

        public void setStudent_name(String student_name) {
            this.student_name = student_name;
        }

        public void setStudent_school(String student_school) {
            this.student_school = student_school;
        }

        public void setStudents( List<String> students) {
            this.students = students;
        }

        public void setSubjects(List<String> subjects) {
            this.subjects = subjects;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getAddress() {
            return address;
        }

        public String getBio() {
            return bio;
        }

        public String getProfile_pic_url() {
            return profile_pic_url;
        }

        public String getStudent_grade() {
            return student_grade;
        }

        public String getStudent_name() {
            return student_name;
        }

        public String getStudent_school() {
            return student_school;
        }

        public List<String> getStudents() {
            return students;
        }

        public List<String> getSubjects() {
            return subjects;
        }

        public String getZipcode() {
            return zipcode;
        }

    }
}
