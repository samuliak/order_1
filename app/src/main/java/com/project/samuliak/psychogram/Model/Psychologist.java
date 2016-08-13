package com.project.samuliak.psychogram.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Psychologist implements Parcelable{

    @SerializedName("login")
    @Expose
    public String login;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("surname")
    @Expose
    public String surname;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("experience")
    @Expose
    public Integer experience;
    @SerializedName("sex")
    @Expose
    public String sex;
    @SerializedName("birthday")
    @Expose
    public String birthday;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("prof_interest")
    @Expose
    public String profInterest;
    @SerializedName("place_of_work")
    @Expose
    public String placeOfWork;
    @SerializedName("university")
    @Expose
    public String university;
    @SerializedName("specialization")
    @Expose
    public String specialization;
    @SerializedName("competence")
    @Expose
    public String competence;
    @SerializedName("direction_of_work")
    @Expose
    public String directionOfWork;
    @SerializedName("online")
    @Expose
    public Boolean online;
    @SerializedName("id")
    @Expose
    public Integer id;

    public Psychologist() {
    }

    public Psychologist(String login, String name, String surname, String password,
                        Integer experience, String sex, String birthday, String country,
                        String city, String profInterest, String placeOfWork, String university,
                        String specialization, String competence, String directionOfWork) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.experience = experience;
        this.sex = sex;
        this.birthday = birthday;
        this.country = country;
        this.city = city;
        this.profInterest = profInterest;
        this.placeOfWork = placeOfWork;
        this.university = university;
        this.specialization = specialization;
        this.competence = competence;
        this.directionOfWork = directionOfWork;
        this.online = false;
    }


    protected Psychologist(Parcel in) {
        login = in.readString();
        name = in.readString();
        surname = in.readString();
        password = in.readString();
        sex = in.readString();
        birthday = in.readString();
        country = in.readString();
        city = in.readString();
        profInterest = in.readString();
        placeOfWork = in.readString();
        university = in.readString();
        specialization = in.readString();
        competence = in.readString();
        directionOfWork = in.readString();
    }

    public static final Creator<Psychologist> CREATOR = new Creator<Psychologist>() {
        @Override
        public Psychologist createFromParcel(Parcel in) {
            return new Psychologist(in);
        }

        @Override
        public Psychologist[] newArray(int size) {
            return new Psychologist[size];
        }
    };

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProfInterest() {
        return profInterest;
    }

    public void setProfInterest(String profInterest) {
        this.profInterest = profInterest;
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public String getDirectionOfWork() {
        return directionOfWork;
    }

    public void setDirectionOfWork(String directionOfWork) {
        this.directionOfWork = directionOfWork;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeString(password);
        dest.writeString(sex);
        dest.writeString(birthday);
        dest.writeString(country);
        dest.writeString(city);
        dest.writeString(profInterest);
        dest.writeString(placeOfWork);
        dest.writeString(university);
        dest.writeString(specialization);
        dest.writeString(competence);
        dest.writeString(directionOfWork);
    }
}