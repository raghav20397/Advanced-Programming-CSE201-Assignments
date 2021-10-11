package cowin_ass1;

// public package com.basics;
import java.util.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.io.*;
class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    static void init(InputStream input) {
        reader = new BufferedReader(new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                    reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextint() throws IOException {
        return Integer.parseInt( next() );
    }

    static long nextlong() throws IOException {
        return Long.parseLong( next() );
    }

    static double nextdouble() throws IOException {
        return Double.parseDouble( next() );
    }
    static int[] nextlist(int l) throws IOException{
        int[] arr=new int[l];
        for(int i=0;i<l;i++){
            arr[i]=Reader.nextint();
        }
        return arr;
    }

}
//citizen
//hospital
//slot
class citizen{
    String citizenName;
    int age;
    String citizenID;
    String vaccinatedWith;
    String status;
    int dosesGot;
    int nextDue;
    citizen(){
        this.status="NOT REGISTERED";
        this.dosesGot=0;
    }
    public void setCName(String cname){
        this.citizenName=cname;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setcID(String citizenID){
        this.citizenID=citizenID;
    }
    public String getCName(){
        return this.citizenName;
    }
    public void setDoses(int num){
        this.dosesGot+=num;
    }
    public int getDoses(){
        return this.dosesGot;
    }
    public void setNextDue(int dateGap,int day){
        this.nextDue+=dateGap+day;
    }
    public int getNextDue(){
        return this.nextDue;
    }
    public void setVaccine(String vaccine){
        this.vaccinatedWith=vaccine;
    }
    public String getVaccine(){
        return this.vaccinatedWith;
    }
    public void setVaccinationStatus(String status){
        if(status=="REGISTERED"){
            this.status="REGISTERED";
        }
        else if(status=="PARTIALLY VACCINATED"){
            this.status="PARTIALLY VACCINATED";
        }
        else if(status=="FULLY VACCINATED"){
            this.status="FULLY VACCINATED";
        }
    }
    public String getVaccinationStatus(){
        return this.status;
    }
}
class vaccine{
    String vaxName;
    int numOfDoses;
    int gap;
    vaccine(){
        gap=0;
    }
    public void setVName(String vname){
        this.vaxName=vname;
    }
    public void setDoses(int numDoses){
        this.numOfDoses=numDoses;
    }
    public void setGap(int gapVax){
        this.gap=gapVax;
    }
    public String getVName(){
        return this.vaxName;
    }
    public int getDoses(){
        return this.numOfDoses;
    }
    public int getGap(){
        return this.gap;
    }
}
class slot{
    int day;
    int quantity;
    String VaxName;
    slot(){
        quantity=0;
    }
    void setDay(int dayNum){
        this.day=dayNum;
    }
    void setQty(int qty){
        this.quantity+=qty;
    }
    void forVax(String VaxName){
        this.VaxName=VaxName;
    }
    // void addSlot(hospital hospitalName){

    // }
}
class hospital{
    String hospName;
    int pincode;
    int hospitalID;
    ArrayList<slot> hospitalSlots= new ArrayList<>();
    hospital(){
        Random giverandom= new Random();
        int hospID= giverandom.nextInt(900000)+ 99999;
        // String a= String.format("%06d", hospID);
        this.hospitalID=hospID;
    }
    void setHName(String hname){
        this.hospName=hname;
    }
    void setPincode(int pin){
        this.pincode=pin;
    }
    void addSlot(int day,int qty,String vaxName){
        slot newSlot = new slot();
        newSlot.setDay(day);
        newSlot.setQty(qty);
        newSlot.forVax(vaxName);
        hospitalSlots.add(newSlot);
    }
    int getSlotQty(int day, String vaxName){
        for(int i=0; i<hospitalSlots.size();i++){
            if(hospitalSlots.get(i).day==day && hospitalSlots.get(i).VaxName==vaxName){
                return hospitalSlots.get(i).quantity;
            }
        }
        return 0;
    }
    void getSlotByVName(String vname){
        for(int i=0; i<hospitalSlots.size();i++){
            if(hospitalSlots.get(i).VaxName==vname){
                System.out.println(this.hospitalID+" "+this.hospName);
            }
            
        }
    }
    void getAllSlots(){
        for(int i=0; i<hospitalSlots.size();i++){
            System.out.println("Day "+hospitalSlots.get(i).day+ " Vaccine: "+hospitalSlots.get(i).VaxName+ " Available Qty: "+getSlotQty(hospitalSlots.get(i).day, hospitalSlots.get(i).VaxName));
        }
    }
    void getAllSlotsNum(){
        
        for(int i=0; i<hospitalSlots.size();i++){
            
                
                System.out.println(i+"->Day: "+hospitalSlots.get(i).day+ " Available Qty: "+getSlotQty(hospitalSlots.get(i).day, hospitalSlots.get(i).VaxName)+ " Vaccine: "+hospitalSlots.get(i).VaxName);
            
        }
        // if(nslots==0){
        //     System.out.println("No slots available.");
        // }
    }
    void bookSlot(citizen citizenxyz){

    }
    
}
class bookSlot{
    public bookSlot(hospital hospitalIn, citizen citizenFor,  slot atSlot, vaccine withVaccine, int day){
        // if(citizenFor.getNextDue()==day){
            if(citizenFor.getVaccinationStatus()=="REGISTERED"){
                citizenFor.setVaccine(withVaccine.getVName());
                if(withVaccine.getDoses()==1){
                    citizenFor.setVaccinationStatus("FULLY VACCINATED");
                    citizenFor.setDoses(1);
                }
                else if(withVaccine.getDoses()>1){
                    citizenFor.setVaccinationStatus("PARTIALLY VACCINATED");
                    citizenFor.setDoses(1);
                }
                for (int i=0;i<hospitalIn.hospitalSlots.size();i++){
                    if(hospitalIn.hospitalSlots.get(i)==atSlot){
                        hospitalIn.hospitalSlots.get(i).quantity-=1;
                    }
                }
                citizenFor.setNextDue(withVaccine.getGap(),day);
                System.out.println(citizenFor.getCName()+" vaccinated with " + withVaccine.getVName());
            }
            else{
                if(citizenFor.getNextDue()==day){
                    if(citizenFor.getVaccinationStatus()=="PARTIALLY VACCINATED"){
                        citizenFor.setVaccine(withVaccine.getVName());
                        if(withVaccine.getDoses()-citizenFor.getDoses()==1){
                            citizenFor.setVaccinationStatus("FULLY VACCINATED");
                            citizenFor.setDoses(1);
                        }
                        else{
                            citizenFor.setVaccinationStatus("PARTIALLY VACCINATED");
                            citizenFor.setDoses(1);
                        }
                        for (int i=0;i<hospitalIn.hospitalSlots.size();i++){
                            if(hospitalIn.hospitalSlots.get(i)==atSlot){
                                hospitalIn.hospitalSlots.get(i).quantity-=1;
                            }
                        }
                        citizenFor.setNextDue(withVaccine.getGap(),day);
                        System.out.println(citizenFor.getCName()+" vaccinated with " + withVaccine.getVName());
                
                    }
                }
                else{System.out.println("Due date and vaccination dates don't match sorry! ");

                }
            }
            // atSlot.setQty(-1);
            
        
        // else{
        //     S;
        // }
    }
    void confirmBooking(){
        ;
    }
}


public class cowin_ass1 {
    static void printMenu(){
        System.out.println("---------------------------------");
        System.out.println("""
                1. Add Vaccine
                2. Register Hospital
                3. Register Citizen
                4. Add Slot for Vaccination
                5. Book Slot for Vaccination
                6. List all slots for a hospital
                7. Check Vaccination Status
                8. Exit""");

    }
    public static void main(String[] args) throws IOException {
        System.out.println("CoWin Portal initialized....");
        ArrayList<vaccine> vaccines = new ArrayList<>();
        ArrayList<hospital> hospitals= new ArrayList<>();
        HashMap<String, citizen> citizens= new HashMap<>();
        HashMap<String, vaccine> vaxnames= new HashMap<>();
        // ArrayList<citizen> citizens= new ArrayList<>();
        // ArrayList<slot> slots= new ArrayList<>();

        Reader.init(System.in);
        while(true){
            printMenu();
            try{
                int choice = Reader.nextint();
                if (choice==1){
                    vaccine newVax= new vaccine();
                    System.out.print("Vaccine Name: ");
                    String vaccineName= Reader.next();
                    newVax.setVName(vaccineName);
                    System.out.print("Number of Doses: ");
                    int numDoses= Reader.nextint();
                    newVax.setDoses(numDoses);
                    if (numDoses>1){
                        System.out.print("Gap between Doses: ");
                        int gap= Reader.nextint();
                        newVax.setGap(gap);
                    }
                    else{newVax.setGap(0);}           

                    vaccines.add(newVax);
                    vaxnames.put(vaccineName, newVax);
                    System.out.println("Vaccine Name: "+newVax.getVName()+" , Number of Doses: "+newVax.getDoses() +", Gap between Doses: "+ newVax.getGap());
                }
                else if (choice==2){
                    hospital newHospital= new hospital();
                    System.out.print("Hospital Name: ");
                    String hospName= Reader.next();
                    newHospital.setHName(hospName);
                    System.out.print("PinCode: ");
                    int pincode= Reader.nextint();
                    newHospital.setPincode(pincode);

                    hospitals.add(newHospital);
                    // System.out.println(hospitals);
                    System.out.println("Hopsital Name: "+newHospital.hospName+" , Pincode: "+newHospital.pincode +", Unique ID: "+ newHospital.hospitalID);
                }
                else if (choice==3){
                    citizen newCitizen= new citizen();
                    System.out.print("Citizen Name: ");
                    String namecitizen= Reader.next();
                    newCitizen.setCName(namecitizen);
                    System.out.print("Age: ");
                    int cAge= Reader.nextint();
                    newCitizen.setAge(cAge);
                    System.out.print("Unique ID: ");
                    String uniqueid= Reader.next();
                    if(citizens.containsKey(uniqueid)){
                        System.out.println("A citizen with same Unique ID already exists!");
                        continue;
                    }
                    if (uniqueid.length()!=12){
                        System.out.println("Please enter valid Unique ID! ");
                        continue;
                    }
                    // long uniqueidlong= Long.parseLong(uniqueid);
                    newCitizen.setcID(uniqueid);
                    if (cAge>18){
                        citizens.put(uniqueid, newCitizen);
                        newCitizen.setVaccinationStatus("REGISTERED");
                    }
                    // System.out.println("list updated");
                    // citizens.add(newCitizen);
                    System.out.println("Citizen Name: "+newCitizen.getCName()+" , Age: "+newCitizen.age +", Unique ID: "+ newCitizen.citizenID);
                    if (cAge<18){
                        System.out.println("Only above 18 are allowed");
                    }
                }
                else if (choice==4){
                    System.out.print("Enter Hospital ID: ");
                    int hospID= Reader.nextint();
                    int found=0;
                    for(int i=0;i<hospitals.size();i++){
                        if(hospitals.get(i).hospitalID == hospID){
                            found=1;
                            System.out.print("Enter number of Slots to be added: ");
                            int numofslots= Reader.nextint();
                            for(int j=1;j<=numofslots;j++){
                                System.out.print("Enter day number: ");
                                int daynum=Reader.nextint();
                                System.out.print("Enter Quantity: ");
                                int vaxqty= Reader.nextint();
                                System.out.println("Select Vaccine ");
                                for(int k=0; k<vaccines.size();k++){
                                    System.out.println(k + ". "+ vaccines.get(k).getVName());
                                }
                                int whichvax= Reader.nextint();
                                
                                hospitals.get(i).addSlot(daynum, vaxqty, vaccines.get(whichvax).getVName());
                                
                                int availableQty=hospitals.get(i).getSlotQty(daynum, vaccines.get(whichvax).getVName());
                                
                                System.out.println("Slot added by Hospital " +hospID +" for Day: "+daynum+", Available Quantity: "+availableQty+" of Vaccine "+vaccines.get(whichvax).getVName());
                            }
                            

                        }
                        
                    }
                    if (found==0){
                        System.out.println("Enter valid Hospital ID");
                    }
                    found=0;
                }
                else if (choice==5){
                    System.out.print("Enter patient Unique ID: ");
                    String uid= Reader.next();
                    if (citizens.containsKey(uid)){
                        // while(true){
                            System.out.println("""
                                1. Search by area
                                2. Search by Vaccine
                                3. Exit""");
                            System.out.print("Enter option: ");    
                            int option=Reader.nextint();
                            if(option==1){
                                System.out.print("Enter PinCode: ");
                                int pin=Reader.nextint();
                                int found=0;
                                for(int i=0;i<hospitals.size();i++){
                                    if(hospitals.get(i).pincode==pin){
                                        found=1;
                                        System.out.println(hospitals.get(i).hospitalID +" "+hospitals.get(i).hospName);
                                    }
                                }
                                if(found==0){
                                    System.out.println("No hospitals found in entered area.");
                                }
                                else{
                                    System.out.print("Enter hospital id: ");
                                    int hospID=Reader.nextint();
                                    // vaccine withVax;
                                    
                                    for(int i=0;i<hospitals.size();i++){
                                        if(hospitals.get(i).hospitalID==hospID){
                                            hospitals.get(i).getAllSlotsNum();
                                            System.out.print("Chose slot: ");
                                            int choose= Reader.nextint();
                                            String vaxname=hospitals.get(i).hospitalSlots.get(choose).VaxName;
                                            int day= hospitals.get(i).hospitalSlots.get(choose).day;
                                            if(citizens.get(uid).getVaccinationStatus()=="REGISTERED"){
                                                // vaccine withVax;
                                                bookSlot booking= new bookSlot(hospitals.get(i), citizens.get(uid), hospitals.get(i).hospitalSlots.get(choose), vaxnames.get(vaxname),day);
                                                System.out.println();
                                                booking.confirmBooking();
                                            }
                                            else if(citizens.get(uid).getVaccinationStatus()=="PARTIALLY VACCINATED"){
                                               
                                                if(citizens.get(uid).getVaccine()!=hospitals.get(i).hospitalSlots.get(choose).VaxName){
                                                    System.out.println("Vaccine mismatch is not allowed!");
                                                }
                                                else{
                                                    // vaccine withVax;
                                                    bookSlot booking= new bookSlot(hospitals.get(i), citizens.get(uid), hospitals.get(i).hospitalSlots.get(choose), vaxnames.get(vaxname),day );
                                                    booking.confirmBooking();
                                                }
                                            }
                                            else if(citizens.get(uid).getVaccinationStatus()=="FULLY VACCINATED"){
                                                System.out.println("You are already vaccinated! ");
                                            }
                                            //handle gap mismatch
                                            // else if(){

                                            // }
                                            
                                        }
                                    }
                                }
                            }
                            else if(option==2){
                                System.out.print("Enter vaccine name: ");
                                String vname= Reader.next();
                                // System.out.println(vname.length()+"length");
                                int foundv=0;
                                for(int i=0;i<hospitals.size();i++){
                                    // System.out.println("loop");
                                    for(int j=0;j<hospitals.get(i).hospitalSlots.size();j++){

                                        // System.out.println(hospitals.get(i).hospitalSlots.get(j).VaxName);
                                        if(vname.equals(hospitals.get(i).hospitalSlots.get(j).VaxName) ){
                                            
                                            // System.out.println("condition true");

                                            System.out.println(hospitals.get(i).hospitalID+ " "+ hospitals.get(i).hospName);
                                            foundv=1;
                                        }
                                    }
                                }
                                // System.out.println(foundv+" after");
                                if (foundv==0){
                                    System.out.println("No such hospitals found.");
                                }
                                else{
                                    System.out.print("Enter hospital ID: ");
                                    int hospID= Reader.nextint();
                                    for(int i=0;i<hospitals.size();i++){
                                        if(hospitals.get(i).hospitalID==hospID){
                                            hospitals.get(i).getAllSlotsNum();
                                            System.out.print("Chose slot: ");
                                            int choose= Reader.nextint();
                                            int day= hospitals.get(i).hospitalSlots.get(choose).day;
                                            if(citizens.get(uid).getVaccinationStatus()=="REGISTERED"){
                                                // vaccine withVax;
                                                bookSlot booking= new bookSlot(hospitals.get(i), citizens.get(uid), hospitals.get(i).hospitalSlots.get(choose), vaxnames.get(vname),day);
                                                System.out.println();
                                                booking.confirmBooking();
                                            }
                                            else if(citizens.get(uid).getVaccinationStatus()=="PARTIALLY VACCINATED"){
                                               
                                                if(citizens.get(uid).getVaccine()!=hospitals.get(i).hospitalSlots.get(choose).VaxName){
                                                    System.out.println("Vaccine mismatch is not allowed!");
                                                }
                                                else{
                                                    // vaccine withVax;
                                                    bookSlot booking= new bookSlot(hospitals.get(i), citizens.get(uid), hospitals.get(i).hospitalSlots.get(choose), vaxnames.get(vname),day );
                                                    booking.confirmBooking();
                                                }
                                            }
                                            else if(citizens.get(uid).getVaccinationStatus()=="FULLY VACCINATED"){
                                                System.out.println("You are already vaccinated! ");
                                            }
                                        }
                                    }
                                }
                            }
                            else if(option==3){
                                continue;
                            }
                            else{
                                System.out.println("Enter a valid option!");
                            }
                        
                    }
                    else{
                        System.out.println("Enter valid Citizen Unique ID.");
                    }
                }
                else if (choice==6){
                    System.out.print("Enter Hospital Id:  ");
                    int hospID= Reader.nextint();
                    for(int i=0;i<hospitals.size();i++){
                        if(hospitals.get(i).hospitalID == hospID){
                            hospitals.get(i).getAllSlots();
                        }                       
                    }
                }
                else if (choice==7){
                    System.out.print("Enter Patient ID: ");
                    String uid= Reader.next();
                    if(citizens.containsKey(uid)){
                        if(citizens.get(uid).getVaccinationStatus()=="REGISTERED"){
                            System.out.println("CITIZEN REGISTERED");
                        }
                        else{
                            System.out.println(citizens.get(uid).getVaccinationStatus());
                            System.out.println("Vaccine given: "+citizens.get(uid).getVaccine());
                            System.out.println("Number of Doses given: "+citizens.get(uid).getDoses());
                            if(citizens.get(uid).getVaccinationStatus()!="FULLY VACCINATED"){
                                System.out.println("Next Dose due date: "+citizens.get(uid).getNextDue());
                            }
                            
                        }
                        
                    }
                    else{
                        System.out.println("User not found. ");
                    }
                }
                else if (choice==8){
                    break;
                }
                else{
                    System.out.println("Enter a valid choice.");
                }
            }
            catch(Exception e){
                System.out.println("Enter a valid choice!");
            }
        }
            
    }
}



