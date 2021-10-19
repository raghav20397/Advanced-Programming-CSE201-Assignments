package Backpack;
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


interface user{
    default void lecturematerial(){
        // @Override
    }
    default void assessment(){

    }
    default void assignment(){

    }
}
interface material{
    static void getall(){};
}
class slide implements material{
    private String topic;
    private int numSlides;
    private Date date;
    private instructor Instructor;
    ArrayList<String> content= new ArrayList<>();
    void setInstructor(instructor Instructor) {
        this.Instructor = Instructor;
    }
    String getInstructor() {
        return this.Instructor.getiName();
    }
    String getTopic(){
        return this.topic;
    }    
    void setDate(Date date) {
        this.date = date;
    }      
    Date getDate() {
        return this.date;
    }
    void setTopic( String topicName){
        this.topic= topicName;
    }      
    int getNumSlides(){
        return this.numSlides;
    }         
    void setNumSlides(int num){
        this.numSlides= num;
    }    
    void addcontent(String contentTopic){
        content.add(contentTopic);
    }
    void getall(){
        System.out.println("Title: "+ this.topic);
        for(int i=0;i<content.size();i++){
            System.out.println("Slide "+i+": "+content.get(i));
        }
        System.out.println("Number of slides: "+content.size());
        System.out.println("Date of upload: "+this.date);
        System.out.println("Uploaded by: "+this.Instructor.getiName());
    }

}
class lectureVideo implements material{
    private String topic;
    private String filename;
    private Date date;
    private instructor Instructor;
    void setDate(Date date){
        this.date=date;
    }
    void setInstructor(instructor Instructor) {
        this.Instructor = Instructor;
    }
    String getInstructor() {
        return this.Instructor.getiName();
    }
    Date getDate() {
        return this.date;
    }
    String getFilename() {
        return this.filename;
    }
    String getTopic() {
        return this.topic;
    }
    void setFilename(String filename) {
        this.filename = filename;
    }
    void setTopic(String topic) {
        this.topic = topic;
    }
    void getall(){
        System.out.println("Title of video: "+ this.topic);
        System.out.println("Video file: "+ this.filename);
        System.out.println("Date of upload: "+this.date);
        System.out.println("Uploaded by: "+this.Instructor.getiName());
    }


}
interface assessment{
    static void getall(){}

}
class assignment implements assessment{
    private String statement;
    private int maxmarks;
    private String upload;
    private String status;
    assignment(){
        status="OPEN";
    }
    
    String getStatus() {
        return this.status;
    }
    void setStatus(String status) {
        this.status = status;
    }
    int getMaxmarks() {
        return this.maxmarks;
    }
    void setUploadfile(String uploadfile) {
        this.upload= uploadfile;
    }
    String getUpload() {
        return this.upload;
    }
    String getStatement() {
        return this.statement;
    }
    void setMaxmarks(int maxmarks) {
        this.maxmarks = maxmarks;
    }
    void setStatement(String statement) {
        this.statement = statement;
    }
    void getall(){
        System.out.println("Assignment: "+ this.statement+ "Max Marks: "+ this.maxmarks);
    }
}
class quiz implements assessment{
    private String question;
    private int maxmarks;
    private String status;
    
    quiz(){
        maxmarks=1;
        status= "OPEN";
    }
    
    void setStatus(String status) {
        this.status = status;
    }
    String getStatus() {
        return this.status;
    }
    int getMaxmarks() {
        return this.maxmarks;
    }
    String getQuestion() {
        return this.question;
    }
    void setMaxmarks(int maxmarks) {
        this.maxmarks = maxmarks;
    }
    void setQuestion(String question) {
        this.question = question;
    }
    void getall(){
        System.out.println("Question: "+ this.question);
        
    }
}
class comment{
    private String by;
    private String at;
    private String comment;
    public comment(){

    }
    void setcommentStudent(student stu, String comm, String time){
        this.by= stu.getsName();
        this.at=time;
        this.comment= comm;
    }
    void setcommentInstructor(instructor ins, String comm, String time){
        this.by=ins.getiName();
        this.at=time;
        this.comment=comm;
    }
    String getBy(){
        return this.by;
    }
    String getAt() {
        return this.at;
    }
    String getComment() {
        return this.comment;
    }
}
class student implements user{
    private String sName;
    ArrayList<assignment> assdone= new ArrayList<>();
    ArrayList<quiz> quizdone= new ArrayList<>();
    student(){

    }
    void setsName(String sName) {
        this.sName = sName;
    }
    String getsName() {
        return this.sName;
    }
    public void lecturematerial(){
        @Override
        
    }
    public void assessment(){
        @Override
    }
    public void assignment(){
        @Override
    }
}
class instructor implements user{
    private String iName;
    instructor(){

    }
    void setiName(String iName) {
        this.iName = iName;
    }
    String getiName() {
        return this.iName;
    }
    public void lecturematerial(){
        @Override
    }
    public void assessment(){
        @Override
    }
    public void assignment(){
        @Override
    }
}


public class backpack {
    static void smallmenu(){
        System.out.println("""
        Welcome to Backpack
        1. Enter as instructor
        2. Enter as student
        3. Exit""");
    }
    static void insmenu(){
        System.out.println("""
        1. Add class material
        2. Add assessments
        3. View lecture materials
        4. View assessments
        5. Grade assessments
        6. Close assessment
        7. View comments
        8. Add comments
        9. Logout
        """);
    }
    static void stumenu(){
        System.out.println("""
        1. View lecture materials
        2. View assessments
        3. Submit assessment
        4. View grades
        5. View comments
        6. Add comments
        7. Logout
        """);
    }
    public static void main(String[] args) throws IOException{
        Reader.init(System.in);
        ArrayList<student> students= new ArrayList<>();
        ArrayList<instructor> instructors= new ArrayList<>();
        ArrayList<slide> slides= new ArrayList<>();
        ArrayList<lectureVideo> lecvideos= new ArrayList<>();
        ArrayList<quiz> quizzes= new ArrayList<>();
        ArrayList<assignment> assignments= new ArrayList<>();
        ArrayList<comment> comments= new ArrayList<>();
        while(true){
            smallmenu();
            try{
                int choice= Reader.nextint();
                if(choice==1){
                    System.out.println("Instructors: ");
                    for(int i=0;i<instructors.size();i++){
                        System.out.println(i+"-"+instructors.get(i).getiName());
                    }
                    System.out.print("Chose ID: ");
                    int id= Reader.nextint();
                    if(id>=0 && id<= instructors.size()){
                        
                        while(true){
                            System.out.println(" ");
                            System.out.println("Welcome "+ instructors.get(id).getiName());
                            insmenu();
                            try{
                                int choiceins= Reader.nextint();
                                if (choiceins==1){
                                    System.out.println("""
                                    1. Add Lecture Slide
                                    2. Add Lecture Video
                                    """);
                                    int nexchoice= Reader.nextint();
                                    if(nexchoice==1){
                                        slide newSlide= new slide();
                                        System.out.print("Enter topic of slides: "); 
                                        String topic= Reader.next();
                                        newSlide.setTopic(topic);
                                        System.out.print("Enter number of slides: ");
                                        int num= Reader.nextint();
                                        newSlide.setNumSlides(num);
                                        System.out.println("Enter content of slides");
                                        for(int i=0;i<num;i++){
                                            System.out.print("Content of slide"+ " "+i+1+": ");
                                            String content= Reader.next();
                                            newSlide.addcontent(content);
                                        }
                                        Date date=new Date();
                                        newSlide.setDate(date);
                                        newSlide.setInstructor(instructors.get(id));
                                        slides.add(newSlide);
                                    }
                                    else if(nexchoice==2){
                                        lectureVideo lecVideo= new lectureVideo();
                                        System.out.print("Enter topic of video: ");
                                        String topicvid= Reader.next();
                                        lecVideo.setTopic(topicvid);
                                        System.out.print("Enter filename of video: ");
                                        String filename=Reader.next();
                                        lecVideo.setFilename(filename);
                                        Date date= new Date();
                                        lecVideo.setDate(date);
                                        lecVideo.setInstructor(instructors.get(id));
                                        if(filename.substring(filename.length()-4).equals(".mp4")){
                                            lecvideos.add(lecVideo);
                                        }
                                        else{
                                            System.out.println("File format not correct! ");
                                        }
                                    }
                                }
                                else if(choiceins==2){
                                    System.out.println("""
                                    1. Add Assignment
                                    2. Add Quiz
                                    """);
                                    int choice2= Reader.nextint();
                                    if(choice2==1){
                                        assignment newass= new assignment();
                                        System.out.print("Enter problem statement: ");
                                        String statement= Reader.next();
                                        newass.setStatement(statement);
                                        System.out.print("Enter max marks: ");
                                        int maxmarks= Reader.nextint();
                                        newass.setMaxmarks(maxmarks);
                                        assignments.add(newass);
                                    }
                                    else if (choice2==2){
                                        System.out.print("Enter quiz question: ");
                                        String question= Reader.next();
                                        quiz newquiz= new quiz();
                                        newquiz.setQuestion(question);
                                        quizzes.add(newquiz);
                                    }
                                }
                                else if(choiceins==3){
                                    for(int i=0;i<slides.size();i++){
                                        slides.get(i).getall();
                                    }
                                    System.out.println(" ");
                                    for(int j=0;j<lecvideos.size();j++){
                                        lecvideos.get(j).getall();
                                    }
                                }
                                else if(choiceins==4){
                                    int idgiver=0;
                                    for(int i=0;i<assignments.size();i++){
                                        System.out.print("ID: "+idgiver+" ");
                                        assignments.get(i).getall();
                                        System.out.println("------------------");
                                        idgiver++;
                                    }
                                    for(int j=0;j<quizzes.size();j++){
                                        System.out.print("ID: "+idgiver+" ");
                                        quizzes.get(j).getall();
                                        System.out.println("------------------");
                                        idgiver++;
                                    }
                                }
                                else if(choiceins==5){

                                }
                                else if(choiceins==6){

                                }
                                else if (choiceins==7){
                                    for(int i=0;i<comments.size();i++){
                                        System.out.println(comments.get(i).getComment()+ comments.get(i).getBy());
                                        System.out.println(comments.get(i).getAt());
                                        System.out.println("");
                                    }
                                }
                                else if(choiceins==8){
                                    System.out.print("Enter comment: ");
                                    String comm= Reader.next();
                                    comment newcomm= new comment();
                                    java.util.Date date= new java.util.Date();
                                    String time= date.toString();
                                    newcomm.setcommentInstructor(instructors.get(id), comm, time);
                                    comments.add(newcomm);
                                }
                                else if(choiceins==9){
                                    break;
                                }
                                else{
                                    System.out.println("Not a valid option!");
                                }
                            }
                            catch(Exception e){
                                System.out.println("Please enter a valid option!");
                            }
                        }
                        
                        
                    }
                    else{
                        System.out.println("Enter valid instructor ID");
                    }

                }
                else if (choice==2){
                    System.out.println("Student: ");
                    for(int i=0;i< students.size();i++){
                        System.out.println(i+" - "+students.get(i).getsName());
                    }
                    System.out.print("Chose ID: ");
                    int id=Reader.nextint();
                    if(id>=0 && id<=students.size()){
                        while(true){
                            System.out.println("Welcome "+students.get(id).getsName());
                            stumenu();
                            int stuchoice= Reader.nextint();
                            if (stuchoice==1){
                                for(int i=0;i<slides.size();i++){
                                    slides.get(i).getall();
                                }
                                System.out.println(" ");
                                for(int j=0;j<lecvideos.size();j++){
                                    lecvideos.get(j).getall();
                                }
                            }
                            else if(stuchoice==2){
                                int idgiver=0;
                                for(int i=0;i<assignments.size();i++){
                                    System.out.print("ID: "+idgiver+" ");
                                    assignments.get(i).getall();
                                    System.out.println("------------------");
                                    idgiver++;
                                }
                                for(int j=0;j<quizzes.size();j++){
                                    System.out.print("ID: "+idgiver+" ");
                                    quizzes.get(j).getall();
                                    System.out.println("------------------");
                                    idgiver++;
                                }
                            }
                            else if(stuchoice==3){
                                System.out.println("Pending assignments:  ");
                                int nothing=0;
                                ArrayList<assignment> asspending=  new ArrayList<>();
                                ArrayList<assessment> ass= new ArrayList<>();
                                ArrayList<quiz> quizpending= new ArrayList<>();
                                HashMap<Integer, assignment> assp = new HashMap<>();
                                HashMap<Integer, quiz> quizp = new HashMap<>();
                                for(int i=0;i<assignments.size();i++){
                                    if(students.get(id).assdone.contains(assignments.get(i))){
                                        nothing+=1;
                                    }
                                    else{
                                        asspending.add(assignments.get(i));
                                        ass.add(assignments.get(i));
                                    }

                                }
                                for(int i=0;i<quizzes.size();i++){
                                    if(students.get(id).quizdone.contains(quizzes.get(i))){
                                        nothing+=1;
                                    }
                                    else{
                                        quizpending.add(quizzes.get(i));
                                        ass.add(quizzes.get(i));
                                    }
                                }
                                int idgiver=0;
                                for(int i=0;i<asspending.size();i++){
                                    assp.put(idgiver, asspending.get(i));
                                    idgiver++;
                                }
                                for(int j=0;j<quizpending.size();j++){
                                    quizp.put(idgiver, quizpending.get(j));
                                    idgiver++;
                                }
                                int counter=0;
                                for(int i=0; i<asspending.size();i++){
                                    System.out.print("ID: "+counter+ " ");
                                    asspending.get(i).getall();
                                    counter++;
                                }
                                for(int i=0; i<quizpending.size();i++){
                                    System.out.print("ID: "+counter+ " ");
                                    quizpending.get(i).getall();
                                    counter++;
                                }
                                if(quizpending.size()+asspending.size()==0){
                                    System.out.println("No pending assignments.");
                                }
                                else{
                                    System.out.println("Enter ID of assessment: ");
                                    int assid= Reader.nextint();
                                    if(quizp.containsKey(assid)){
                                        System.out.print(quizp.get(assid).getQuestion());
                                        String answer=Reader.next();
                                        String[] wordarray= answer.split("\\s+");
                                        int wordcount= wordarray.length;
                                        if (wordcount==1){
                                            students.get(id).quizdone.add(quizp.get(assid));
                                        }
                                        else{
                                            System.out.println("Quiz asnwers can only be of one word! ");
                                        }                                
                                    }
                                    else if(assp.containsKey(assid)){
                                        System.out.println(assp.get(assid).getStatement());
                                        System.out.print("Enter filename of assignment: ");
                                        String file= Reader.next();
                                        if(file.substring(file.length()-4).equals(".zip")){
                                            students.get(id).assdone.add(assp.get(assid));
                                        }
                                    }
                                
                                nothing=0;
                                }
                                
                            }
                            else if(stuchoice==4){}
                            else if(stuchoice==5){
                                for(int i=0;i<comments.size();i++){
                                    System.out.println(comments.get(i).getComment() + comments.get(i).getBy());
                                    System.out.println(comments.get(i).getAt());
                                    System.out.println("");
                                }
                            }
                            else if(stuchoice==6){
                                System.out.print("Enter comment: ");
                                String comm= Reader.next();
                                comment newcomm= new comment();
                                java.util.Date date= new java.util.Date();
                                String time= date.toString();
                                newcomm.setcommentInstructor(instructors.get(id), comm, time);
                                comments.add(newcomm);
                            }
                            else if(stuchoice==7){
                                break;
                            }
                            else{
                                System.out.println("Please chose a valid option!");
                            }
                        }
                        
                    }
                    else{
                        System.out.println("Enter valid student ID");
                    }
                }
                else if (choice==3){
                    break;
                }
                else if(choice==4){
                    System.out.print("Instructor name: ");
                    String iname= Reader.next();
                    instructor instruc= new instructor();
                    instruc.setiName(iname);
                    instructors.add(instruc);
                }
                else if(choice==5){
                    System.out.print("Student name: ");
                    String sName= Reader.next();
                    student studen= new student();
                    studen.setsName(sName);
                    students.add(studen);

                }
                else{
                    System.out.println("Enter a valid option");
                }
            }
            catch(Exception e){
                System.out.println("Enter a valid choice!");
            }
        }
        
        
    }
}
