package controller;

import action.CRUDaction;
import controller.Check;
import model.Agency;

import java.util.ArrayList;

public class AgencyController implements CRUDaction {
    static ArrayList<Agency> agencyList;

    public AgencyController(ArrayList<Agency> agencyListl) {
        this.agencyList = agencyListl;
    }


    public void add(){
        agencyList.add(new Agency(1,"Thành Nguyễn","88 Đức Minh - Thanh Bình - Thành phố Hải Dương","0972645112"));
    }

    @Override
    public void display() {
        System.out.printf("%s%20s%40s%40s%n","ID","Name","Address","Phone");
        for (Agency agency : agencyList) {
            System.out.printf("%d%24s%60s%19s%n", agency.getAgencyID(), agency.getAgencyName(),agency.getAgencyAddress(),agency.getAgencyPhone());
        }
    }

    @Override
    public void input() {

    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

}
