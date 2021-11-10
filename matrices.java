// package APass3;
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


class matrix{
    protected int rows;
    protected int cols;
    protected int[][] matrix= new int[rows][cols];
    protected int isit;
    protected String typeofmatrix;
    protected ArrayList<String> matrixtypes= new ArrayList<>();
    matrix(){
        
    }
    void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
    int[][] getMatrix() {
        return matrix;
    }
    void setRows(int rows) {
        this.rows = rows;
    }
    void setCols(int cols) {
        this.cols = cols;
    }
    void getMatrixTypes(){
        for(int i=0;i<matrixtypes.size();i++){
            System.out.println(matrixtypes.get(i));
        }
    }

    void printmatrix(){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    void transpose(){
        System.out.println("Transposed matrix: ");
        for(int i=0;i<cols;i++){
            for(int j=0;j<rows;j++){
                System.out.print(matrix[j][i] + " ");
            }
            System.out.println();
        }
        
    }
    int[][] gettranspose(){
        int[][] tr= new int[cols][rows];
        for(int i=0;i<cols;i++){
            for(int j=0;j<rows;j++){
                tr[i][j]=matrix[j][i] ;
            }
        }
        return tr;
    }
    int getdeterminant(){
        if(rows==1&&cols==1){
            return matrix[0][0];
        }
        else if(rows==2&&cols==2){
            int det= (matrix[0][0]*matrix[1][1]) -(matrix[1][0]*matrix[0][1]);
            return det;
        }
        else if(rows==3&&cols==3){
            int det = (matrix[0][0]*(matrix[1][1]*matrix[2][2]-matrix[1][2]*matrix[2][1])) -(matrix[0][1]*(matrix[1][0]*matrix[2][2]-matrix[1][2]*matrix[2][0])) +(matrix[0][2]*(matrix[1][0]*matrix[2][1]-matrix[1][1]*matrix[2][0]));
            return det;
        }
        else{
            System.out.println("Not a square matrix!");
            return -1;
        }
    }
    int getScalar(){
        return 0;
    }
    int getcofactor(int r, int c){
        return 0;
    }
    float[][] getinverse(){
        float[][] inv= new float[rows][cols];
        if (rows==1 && cols==1){
            float[][] dupl={{(float)matrix[0][0]}};
            return dupl;
        }
        else if (rows==2&& cols==2){
            int determinant= getdeterminant();
            inv[0][0]=matrix[1][1]/determinant;
            inv[0][1]=- (float)matrix[0][1]/determinant;
            inv[1][1]=(float)matrix[0][0]/determinant;
            inv[1][0]=- (float)matrix[1][0]/determinant;
            return inv;
        }
        else if(rows==3&&cols==3){
            float[][] inv3= new float[rows][cols];
            int determinant= getdeterminant();
            for(int i = 0; i < 3; ++i) {
                for(int j = 0; j < 3; ++j){
                    inv3[i][j]=((float)((matrix[(j+1)%3][(i+1)%3] * matrix[(j+2)%3][(i+2)%3]) - (matrix[(j+1)%3][(i+2)%3] * matrix[(j+2)%3][(i+1)%3]))/ determinant) ;
                    // System.out.print((((matrix[(j+1)%3][(i+1)%3] * matrix[(j+2)%3][(i+2)%3]) - (matrix[(j+1)%3][(i+2)%3] * matrix[(j+2)%3][(i+1)%3]))/ determinant) + " ");
                    // System.out.print("\n");
                }
            }
            return inv3;
        }
        return inv;
    }
    ArrayList<String> checkfortypes(){
        ArrayList<String> types= new ArrayList<>();
        if(rows==cols){
            int nullvar=0;
            int onesvar=0;
            int idvar=0;
            int idnullvar=0;
            int scalvar=0;
            int scnullvar=0;
            int diagvar=0;
            int symmvar=0;
            int skewsymmvar=0;
            for(int i=0; i<rows;i++){
                for(int j=0;j<cols;j++){
                    if (matrix[i][j]==0){
                        nullvar+=1;
                    }
                }
            }        
            for(int i=0; i<rows;i++){
                for(int j=0;j<cols;j++){
                    if (matrix[i][j]==1){
                        onesvar+=1;
                    }
                }
            }
            int first=matrix[0][0];
            for(int i=0; i<rows;i++){
                for(int j=0;j<cols;j++){
                    if (matrix[i][j]==0 && i!=j){
                        scnullvar+=1;
                    }
                }
                if(matrix[i][i]==first){
                    scalvar+=1;
                }
            }
            for(int i=0; i<rows;i++){
                for(int j=0;j<cols;j++){
                    if(matrix[i][i]==1){
                        idvar+=1;
                    }
                    else{
                        idvar+=0;
                    }
                }
            }
            for(int i=0; i<rows;i++){
                for(int j=0;j<cols;j++){
                    if(matrix[i][j]==0){
                        idnullvar+=1;
                    }
                    else{
                        idnullvar+=0;
                    }
                }
            }
            // System.out.println("Idnullvar"+ idnullvar);
            // System.out.println("Idvar"+ idvar);
            for(int i=0; i<rows;i++){
                for(int j=0;j<cols;j++){
                    if (matrix[i][j]==0 && i!=j){
                        diagvar+=1;
                    }
                }
                
            }
            for(int i=0; i<rows;i++){
                for(int j=0;j<cols;j++){
                    if(matrix[i][j]==matrix[j][i]){
                        symmvar+=1;
                    }
                }
            }
            for(int i=0; i<rows;i++){
                for(int j=0;j<cols;j++){
                    if(matrix[i][j]== -matrix[j][i] ){
                        skewsymmvar+=1;
                    }
                }
            }
            if (nullvar==rows*cols){     
                types.add("Null matrix");
            }
            if (onesvar==rows*cols){
                types.add("Ones matrix");   
            }
            if(idvar==rows*rows &&idnullvar==((rows*cols) - rows)){
                types.add("Identity matrix");
            }
            if(rows==1 && cols==1){
                types.add("Singleton matrix");
            }
            if(scalvar==rows && scnullvar==((rows*cols) - rows)){
                types.add("Scalar matrix");
            }
            if(diagvar==((rows*cols) - rows)){
                types.add("Diagonal matrix");
            }
            if(rows==cols){
                int singvar=getdeterminant();
                if(singvar==0){
                    types.add("Singular matrix");
                }
            }
            if(rows==1&&cols==1){
                types.add("Lower Triangular matrix");
                types.add("Upper Triangular matrix");
            }
            if(rows==2 && cols==2){
                if (matrix[0][1]==0){
                    types.add("Lower Triangular matrix");
                }
                if(matrix[1][0]==0){
                    types.add("Upper Triangular matrix");
                }
            }
            if(rows==3 && cols==3){
                if(matrix[0][1]==0 && matrix[0][2]==0 && matrix[1][2]==0 ){
                    types.add("Lower Triangular matrix");
                }   
                if(matrix[1][0]==0 && matrix[2][0]==0 && matrix[2][1]==0 ){
                    types.add("Upper Triangular matrix");
                }   
            }
            if(symmvar== rows*cols){
                types.add("Symmetric matrix");
            }
            if(skewsymmvar== rows*cols){
                types.add("Skew Symmetric matrix");
            }
            if(rows==cols){
                types.add("Square matrix");

            }
        }
        
        int nullvar=0;
        int onesvar=0;
        for(int i=0; i<rows;i++){
            for(int j=0;j<cols;j++){
                if (matrix[i][j]==0){
                    nullvar+=1;
                }
            }
        }        
        for(int i=0; i<rows;i++){
            for(int j=0;j<cols;j++){
                if (matrix[i][j]==1){
                    onesvar+=1;
                }
            }
        }
        if (nullvar==rows*cols){     
            types.add("Null matrix");
        }
        if (onesvar==rows*cols){
            types.add("Ones matrix");   
        }
        if(rows==1){
            types.add("Row matrix");
        }
        if(cols==1){
            types.add("Column matrix");
        }
        if(rows!=cols){
            types.add("Rectangular matrix");
        }
        return types;
    }   
}  
class rectmatrix extends matrix{ 
    rectmatrix(int rows, int cols){
        this.isit=0;
        this.rows=rows;
        this.cols=cols;
        this.matrix=new int[rows][cols];
        this.typeofmatrix="Rectangular Matrix";
    }
}
class rowmatrix extends matrix{
    rowmatrix(int cols){
        this.rows=1;
        this.cols=cols;
        this.matrix=new int[1][cols];
        this.typeofmatrix="Row matrix";
    }
}
class colmatrix extends matrix{
    colmatrix(int rows){
        this.cols=1;
        this.rows=rows;
        this.matrix=new int[rows][1];
        this.typeofmatrix="Column matrix";
    }
}
class squarematrix extends matrix{
    squarematrix(int rows){
        this.rows=rows;
        this.cols=rows;
        this.matrix=new int[rows][rows];
        this.typeofmatrix="Square matrix";
    }
}
class symmatrix extends matrix{
    symmatrix(int rows){
        this.rows=rows;
        this.cols=rows;
        this.matrix=new int[rows][rows];
        this.typeofmatrix="Symmetric matrix";
    }
    @Override
    void transpose() {
        // TODO Auto-generated method stub
        System.out.println("Transposed matrix: ");
        printmatrix();
    }
}
class skewmatrix extends matrix{
    skewmatrix(int rows){
        this.rows=rows;
        this.cols=rows;
        this.matrix=new int[rows][rows];
        this.typeofmatrix="Skew Symmetric matrix";
    }
    @Override
    void transpose() {
        // TODO Auto-generated method stub
        System.out.println("Transposed matrix: ");
        for(int i=0; i<rows;i++){
            for(int j=0;j<cols;j++){
                System.out.println(-matrix[j][i] + " ");
            }
            System.out.println();
        }
    }
}

class uptrimatrix extends matrix{
    uptrimatrix(int rows){
        this.rows=rows;
        this.cols=rows;
        this.matrix=new int[rows][rows];
        this.typeofmatrix="Upper Triangular matrix";
    }
    // 
}
class lowtrimatrix extends matrix{
    lowtrimatrix(int rows){
        this.rows=rows;
        this.cols=rows;
        this.matrix=new int[rows][rows];
        this.typeofmatrix="Lower Triangular matrix";
    }
    
}
class singularmatrix extends matrix{
    singularmatrix(int rows){
        this.rows=rows;
        this.cols=rows;
        this.matrix=new int[rows][rows]; 
        this.typeofmatrix="Singular matrix";
    }
    
}
class diagmatrix extends matrix{
    diagmatrix(int rows, int[] diag){
        this.rows=rows;
        this.cols=rows;
        this.matrix=new int[rows][rows]; 
        this.typeofmatrix="Diagonal matrix";
        for(int i=0;i<rows;i++){
            for(int j=0;j<rows;j++){
                if(i!=j){
                    this.matrix[i][j]=0;
                }
                else{
                    this.matrix[0][0]=diag[0];
                    this.matrix[1][1]=diag[1];
                    this.matrix[2][2]=diag[2];
                }
            }
        }
    }
    void transpose() {
        
        super.transpose();
        // System.out.println("Transposed matrix: ");
        // printmatrix();
    }
}
class scalarmatrix extends matrix{
    private int scint;
    scalarmatrix(int rows,int scint){
        this.rows=rows;
        this.cols=rows;
        this.scint=scint;
        this.matrix=new int[rows][rows]; 
        this.typeofmatrix="Scalar matrix";
        for(int i=0;i<rows;i++){
            for(int j=0;j<rows;j++){
                if(i!=j){
                    this.matrix[i][j]=0;
                }
                else{
                    this.matrix[i][j]=scint;
                }
            }
        }
    }
    int getScint() {
        return scint;
    }
    void transpose() {
        
        super.transpose();
        // System.out.println("Transposed matrix: ");
        // printmatrix();
    }
}
class idmatrix extends matrix{
    idmatrix(int rows){
        this.rows=rows;
        this.cols=rows;
        this.matrix=new int[rows][rows]; 
        this.typeofmatrix="Identity matrix";
        for(int i=0;i<rows;i++){
            for(int j=0;j<rows;j++){
                if(i!=j){
                    this.matrix[i][j]=0;
                }
                else{
                    this.matrix[i][j]=1;
                }
            }
        }   

    }
    void transpose() {
        
        super.transpose();
        // System.out.println("Transposed matrix: ");
        // printmatrix();
    }
}
class singletonmatrix extends matrix{
    int scalar;
    singletonmatrix(int e){
        this.rows=1;
        this.cols=1;
        this.scalar=e;
        this.matrix=new int[rows][cols];
        this.typeofmatrix="Singleton matrix";
        this.matrix[0][0]=e;
    }
    @Override
    int getScalar() {
        return this.scalar;
    }
    void transpose() {
        
        // super.transpose();
        System.out.println("Transposed matrix: ");
        printmatrix();
    }
}
class onesmatrix extends matrix{
    onesmatrix(int rows, int cols){
        this.rows=rows;
        this.cols=cols;
        this.matrix=new int[rows][cols];
        this.typeofmatrix="Ones matrix";
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                this.matrix[i][j]=1;
            }
        }
    }
    @Override
    void transpose() {
        
        // super.transpose();
        System.out.println("Transposed matrix: ");
        printmatrix();
    }
    
}
class nullmatrix extends matrix{
    nullmatrix(int rows, int cols){
        this.rows=rows;
        this.cols=cols;
        this.matrix=new int[rows][cols];
        this.typeofmatrix="Null matrix";
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                this.matrix[i][j]=0;
            }
        }
    }
    
    @Override
    void transpose() {
        
        // super.transpose();
        System.out.println("Transposed matrix: ");
        printmatrix();
    }
}
public class matrices {
    static void showmenu(){
        System.out.println("""
        1. Input matrix
        2. Get matrix of a requested type
        3. Change elements of a matrix
        4. Display matrix-types of a matrix
        5. Matrix addition, subtraction, multiplication, division
        6. Element wise operations
        7. Transpose matrix
        8. Inverse matrix
        9. Compute means                                                                 
        10. Compute determinants
        11. Use singleton matrix in matrix operations
        12. Compute A+A^T for a matrix
        13. Compute Eigen vectors and values
        14. Solve sets of linear equations using matrices
        15. Retrieve all the existing matrices from requesting matrix-type labels
        16. Exit

        Enter option: """);
    }
    static boolean sameorder(matrix m1,matrix m2){
        if(m1.rows==m2.rows && m1.cols==m2.cols){
            return true;
        }
        else return false;
    }
    static void printmatx(int[][] darray){
        
    }
    static void addtypes(matrix mx,ArrayList<String> alltypes){
        for(int i=0;i<alltypes.size();i++){
            if(mx.matrixtypes.contains(alltypes.get(i))==false){
                mx.matrixtypes.add(alltypes.get(i));
            }
        }
    }

// for(int i=0; i<rows;i++){
//     for(int j=0;j<cols;j++){ 
//     }
// }

    public static void main(String[] args) throws IOException{
        ArrayList<matrix> allmatrices= new ArrayList<>();   
        Reader.init(System.in);
        while(true){
            System.out.println("_________________________________________________________________");
            showmenu();
            
            try{
                int choice= Reader.nextint();
                if(choice==1){
                    // int nullvar=0;
                    // int onesvar=0;
                    System.out.println("Enter dimensions of your matrix: ");
                    System.out.println("Number of Rows: ");
                    int r=Reader.nextint();
                    System.out.println("Number of columns: ");
                    int c= Reader.nextint();
                    System.out.println("Enter matrix row by row: ");
                    int[][] mtrxinput= new int[r][];
                    for(int i=0;i<r;i++){
                        int[] row = Reader.nextlist(c);
                        mtrxinput[i]=row;
                    }
                    
                    matrix newmatr= new matrix();
                    newmatr.setCols(c);
                    newmatr.setRows(r);
                    newmatr.setMatrix(mtrxinput);
                    // System.out.println("Trial only: ");
                    // newmatr.printmatrix();
                    ArrayList<String> alltypes= newmatr.checkfortypes();
                    // for(int i=0;i<alltypes.size();i++){
                    //     System.out.println(alltypes.get(i));
                    // }
                    
                    if(alltypes.contains("Null matrix")){
                        matrix newnull= new nullmatrix(r,c);
                        for(int i=0;i<alltypes.size();i++){
                            if(newnull.matrixtypes.contains(alltypes.get(i))==false){
                                newnull.matrixtypes.add(alltypes.get(i));
                            }
                        }
                        // newmatr.setMatrix(mtrxinput);
                        allmatrices.add(newnull);
                    }
                    else if(alltypes.contains("Ones matrix")){
                        matrix newones= new onesmatrix(r,c);
                        // newmatr.setMatrix(mtrxinput);
                        for(int i=0;i<alltypes.size();i++){
                            if(newones.matrixtypes.contains(alltypes.get(i))==false){
                                newones.matrixtypes.add(alltypes.get(i));
                            }
                        }
                        allmatrices.add(newones);
                    }
                    else if(alltypes.contains("Identity matrix")){
                        matrix newid = new idmatrix(r);
                        for(int i=0;i<alltypes.size();i++){
                            if(newid.matrixtypes.contains(alltypes.get(i))==false){
                                newid.matrixtypes.add(alltypes.get(i));
                            }
                        }
                        allmatrices.add(newid);
                    }
                    else if(alltypes.contains("Singleton matrix")){
                        matrix singmatr= new singletonmatrix(mtrxinput[0][0]);
                        for(int i=0;i<alltypes.size();i++){
                            if(singmatr.matrixtypes.contains(alltypes.get(i))==false){
                                singmatr.matrixtypes.add(alltypes.get(i));
                            }
                        }
                        allmatrices.add(singmatr);
                    }
                    else if(alltypes.contains("Scalar matrix")){
                        matrix scal= new scalarmatrix(r, mtrxinput[0][0]);
                        for(int i=0;i<alltypes.size();i++){
                            if(scal.matrixtypes.contains(alltypes.get(i))==false){
                                scal.matrixtypes.add(alltypes.get(i));
                            }
                        }
                        allmatrices.add(scal);
                    }
                    else if(alltypes.contains("Diagonal matrix")){
                        int[] d={mtrxinput[0][0],mtrxinput[1][1],mtrxinput[2][2]};
                        matrix diagmatr= new diagmatrix(r, d);
                        for(int i=0;i<alltypes.size();i++){
                            if(diagmatr.matrixtypes.contains(alltypes.get(i))==false){
                                diagmatr.matrixtypes.add(alltypes.get(i));
                            }
                        }
                        allmatrices.add(diagmatr);
                    }
                    else if(alltypes.contains("Singular matrix")){
                        matrix sing=new singularmatrix(r);
                        sing.setMatrix(mtrxinput);
                        for(int i=0;i<alltypes.size();i++){
                            if(sing.matrixtypes.contains(alltypes.get(i))==false){
                                sing.matrixtypes.add(alltypes.get(i));
                            }
                        }
                        allmatrices.add(sing);
                    }
                    else if(alltypes.contains("Lower Triangular matrix")){
                        matrix ltri= new lowtrimatrix(r);
                        ltri.setMatrix(mtrxinput);
                        for(int i=0;i<alltypes.size();i++){
                            if(ltri.matrixtypes.contains(alltypes.get(i))==false){
                                ltri.matrixtypes.add(alltypes.get(i));
                            }
                        }
                        allmatrices.add(ltri);
                    }
                    else if(alltypes.contains("Upper Triangular matrix")){
                        matrix utri= new uptrimatrix(r); 
                        utri.setMatrix(mtrxinput);
                        for(int i=0;i<alltypes.size();i++){
                            if(utri.matrixtypes.contains(alltypes.get(i))==false){
                                utri.matrixtypes.add(alltypes.get(i));
                            }
                        }
                        allmatrices.add(utri);
                    }
                    else if(alltypes.contains("Symmetric matrix")){
                        matrix sym= new symmatrix(r);
                        sym.setMatrix(mtrxinput);
                        for(int i=0;i<alltypes.size();i++){
                            if(sym.matrixtypes.contains(alltypes.get(i))==false){
                                sym.matrixtypes.add(alltypes.get(i));
                            }
                        }
                        allmatrices.add(sym);
                    }
                    else if(alltypes.contains("Skew Symmetric matrix")){
                        matrix skewsym= new skewmatrix(r);
                        skewsym.setMatrix(mtrxinput);
                        for(int i=0;i<alltypes.size();i++){
                            if(skewsym.matrixtypes.contains(alltypes.get(i))==false){
                                skewsym.matrixtypes.add(alltypes.get(i));
                            }
                        }
                        allmatrices.add(skewsym);
                    }
                    else if(alltypes.contains("Square matrix")){
                        matrix sqma= new squarematrix(r);
                        sqma.setMatrix(mtrxinput);
                        for(int i=0;i<alltypes.size();i++){
                            if(sqma.matrixtypes.contains(alltypes.get(i))==false){
                                sqma.matrixtypes.add(alltypes.get(i));
                            }
                        }
                        allmatrices.add(sqma);
                    }
                    else if(alltypes.contains("Row matrix")){
                        matrix rowm= new rowmatrix(c);
                        rowm.setMatrix(mtrxinput);
                        for(int i=0;i<alltypes.size();i++){
                            if(rowm.matrixtypes.contains(alltypes.get(i))==false){
                               rowm.matrixtypes.add(alltypes.get(i));
                            }
                        }
                        allmatrices.add(rowm);
                    }
                    else if(alltypes.contains("Column matrix")){
                        matrix colm= new colmatrix(r);
                        colm.setMatrix(mtrxinput);
                        for(int i=0;i<alltypes.size();i++){
                            if(colm.matrixtypes.contains(alltypes.get(i))==false){
                                colm.matrixtypes.add(alltypes.get(i));
                            }
                        }
                        allmatrices.add(colm);
                    }
                    else if(alltypes.contains("Rectangular matrix")){
                        matrix recm= new rectmatrix(r, c);
                        recm.setMatrix(mtrxinput);
                        for(int i=0;i<alltypes.size();i++){
                            if(recm.matrixtypes.contains(alltypes.get(i))==false){
                                recm.matrixtypes.add(alltypes.get(i));
                            }
                        }
                        allmatrices.add(recm);
                    }
                    System.out.println("Matrix stored!");
                }





                else if(choice==2){
                    System.out.println();
                    System.out.println("All the matrix types: ");
                    System.out.println("""
                    1.  Rectangular matrix
                    2.  Row matrix
                    3.  Column matrix
                    4.  Square matrix
                    5.  Symmetric matrix
                    6.  Skew Symmetric matrix
                    7.  Upper Triangular matrix
                    8.  Lower Triangular matrix
                    9.  Singular matrix
                    10.  Diagonal matrix
                    11.  Scalar matrix
                    12.  Identity matrix
                    13.  Singleton matrix
                    14.  Ones matrix
                    15.  Null matrix                  
                    """);
                    // String[] types= {"Rectangular matrix","Row matrix","Column matrix","Square matrix","Symmetric matrix","Skew Symmetric matrix","Upper Triangular matrix","Lower Triangular matrix","Singular matrix","Diagonal matrix","Scalar matrix","Identity matrix","Singleton matrix","Ones matrix","Null matrix"};
                    System.out.println();
                    System.out.println("Enter the ID of type of matrix you want to create : ");
                    int inp=Reader.nextint();
                    if(inp==1){
                        matrix mx= new rectmatrix(2, 3);
                        int[][] mxi= {{1,2,3,},{3,4,5,}};            
                        mx.setMatrix(mxi);
                        ArrayList<String> alltypes=mx.checkfortypes();
                        addtypes(mx, alltypes);
                        // for(int i=0;i<alltypes.size();i++){
                        //     if(mx.matrixtypes.contains(alltypes.get(i))==false){
                        //         mx.matrixtypes.add(alltypes.get(i));
                        //     }
                        // }
                        
                        allmatrices.add(mx);
                        System.out.println("Required matrix created and added: ");
                        mx.printmatrix();
                    }
                    else if(inp==2){
                        matrix mx= new rowmatrix(3);
                        int[][] mxi= {{1,2,3}};
                        mx.setMatrix(mxi);
                        ArrayList<String> alltypes=mx.checkfortypes();
                        addtypes(mx, alltypes);
                        allmatrices.add(mx);
                        System.out.println("Required matrix created and added: ");
                        mx.printmatrix();
                    }
                    else if(inp==3){
                        matrix mx= new colmatrix(3);
                        int[][] mxi= {{4},{8},{12}};
                        mx.setMatrix(mxi);
                        ArrayList<String> alltypes=mx.checkfortypes();
                        addtypes(mx, alltypes);
                        allmatrices.add(mx);
                        System.out.println("Required matrix created and added: ");
                        mx.printmatrix();
                    }
                    else if(inp==4){
                        matrix mx= new squarematrix(3);
                        int[][] mxi= {{1,2,3},{3,6,8},{6,7,1}};
                        mx.setMatrix(mxi);
                        ArrayList<String> alltypes=mx.checkfortypes();
                        addtypes(mx, alltypes);
                        allmatrices.add(mx);
                        System.out.println("Required matrix created and added: ");
                        mx.printmatrix();
                    }
                    else if(inp==5){
                        matrix mx= new symmatrix(3);
                        int[][] mxi= {{1,4,2},{4,2,0},{2,0,3}};
                        mx.setMatrix(mxi);
                        ArrayList<String> alltypes=mx.checkfortypes();
                        addtypes(mx, alltypes);
                        allmatrices.add(mx);
                        System.out.println("Required matrix created and added: ");
                        mx.printmatrix();
                    }
                    else if(inp==6){
                        matrix mx= new skewmatrix(3);
                        int[][] mxi= {{0,4,-2},{-4,0,-3},{2,3,0}};
                        mx.setMatrix(mxi);
                        ArrayList<String> alltypes=mx.checkfortypes();
                        addtypes(mx, alltypes);
                        allmatrices.add(mx);
                        System.out.println("Required matrix created and added: ");
                        mx.printmatrix();
                    }
                    else if(inp==7){
                        matrix mx= new uptrimatrix(3);
                        int[][] mxi= {{1,3,5},{0,7,9},{0,0,8}};
                        mx.setMatrix(mxi);
                        ArrayList<String> alltypes=mx.checkfortypes();
                        addtypes(mx, alltypes);
                        allmatrices.add(mx);
                        System.out.println("Required matrix created and added: ");
                        mx.printmatrix();
                    }
                    else if(inp==8){
                        matrix mx= new lowtrimatrix(3);
                        int[][] mxi= {{1,0,0},{2,3,0},{4,5,6}};
                        mx.setMatrix(mxi);
                        ArrayList<String> alltypes=mx.checkfortypes();
                        addtypes(mx, alltypes);
                        allmatrices.add(mx);
                        System.out.println("Required matrix created and added: ");
                        mx.printmatrix();
                    }
                    else if(inp==9){
                        matrix mx= new singularmatrix(3);
                        int[][] mxi= {{3,8,1},{-4,1,1},{-4,1,1}};
                        mx.setMatrix(mxi);
                        ArrayList<String> alltypes=mx.checkfortypes();
                        addtypes(mx, alltypes);
                        allmatrices.add(mx);
                        System.out.println("Required matrix created and added: ");
                        mx.printmatrix();
                    }
                    else if(inp==10){
                        int[] diag={1,6,8};
                        matrix mx= new diagmatrix(3,diag);
                        ArrayList<String> alltypes=mx.checkfortypes();
                        addtypes(mx, alltypes);
                        allmatrices.add(mx);
                        System.out.println("Required matrix created and added: ");
                        mx.printmatrix();
                    }
                    else if(inp==11){
                        matrix mx= new scalarmatrix(3,8);       
                        ArrayList<String> alltypes=mx.checkfortypes();
                        addtypes(mx, alltypes);
                        allmatrices.add(mx);  
                        System.out.println("Required matrix created and added: ");
                        mx.printmatrix();
                    }
                    else if(inp==12){
                       
                        matrix mx= new idmatrix(3);
                        ArrayList<String> alltypes=mx.checkfortypes();
                        addtypes(mx, alltypes);
                        allmatrices.add(mx);
                        System.out.println("Required matrix created and added: ");
                        mx.printmatrix();
                    }   
                    else if(inp==13){
                        matrix mx= new singletonmatrix(5);
                        ArrayList<String> alltypes=mx.checkfortypes();
                        addtypes(mx, alltypes);
                        allmatrices.add(mx);
                        System.out.println("Required matrix created and added: ");
                        mx.printmatrix();
                    }
                    else if(inp==14){
                        matrix mx= new onesmatrix(3, 3);
                        ArrayList<String> alltypes=mx.checkfortypes();
                        addtypes(mx, alltypes);
                        allmatrices.add(mx);
                        System.out.println("Required matrix created and added: ");
                        mx.printmatrix();
                    }
                    else if(inp==15){
                        matrix mx= new nullmatrix(3,3);
                        ArrayList<String> alltypes=mx.checkfortypes();
                        addtypes(mx, alltypes);
                        allmatrices.add(mx);
                        System.out.println("Required matrix created and added: ");
                        mx.printmatrix();
                    }
                }





                else if(choice==3){
                    System.out.println("The input matrices are: ");
                    for(int i=0; i<allmatrices.size();i++){
                        System.out.print(i + "." +"\n" );
                        allmatrices.get(i).printmatrix();
                        System.out.println();
                    }
                    System.out.println("Enter ID of Matrix: ");
                    int ch= Reader.nextint();
                    matrix duplicate=new matrix();
                    duplicate=allmatrices.get(ch);
                    System.out.println("Enter number of elements you want to change: ");
                    int num= Reader.nextint();
                    for(int i=0;i<num;i++){
                        System.out.println("Enter row and column index of element to change (0-based): ");
                        int[] coord= Reader.nextlist(2);
                        int x=coord[0];
                        int y=coord[1];
                        System.out.println("Enter the new element: ");
                        int newi= Reader.nextint();                     
                        duplicate.matrix[x][y]=newi;
                        

                    }
                    ArrayList<String> dupltypes= duplicate.checkfortypes();
                    ArrayList<String> ogtypes= allmatrices.get(ch).matrixtypes;
                    if(dupltypes.containsAll(ogtypes) && ogtypes.containsAll(dupltypes)){
                        allmatrices.get(ch).matrix=duplicate.matrix;
                        System.out.println("Matrix updated! ");
                        System.out.println("New matrix: ");
                        allmatrices.get(ch).printmatrix();
                    }
                    else{
                        System.out.println("Matrix not updated because matrix type changed! ");
                    }
                }






                else if(choice==4){
                    System.out.println("The input matrices are: ");
                    for(int i=0; i<allmatrices.size();i++){
                        System.out.print(i + "." +"\n" );
                        allmatrices.get(i).printmatrix();
                        System.out.println();
                    }
                    System.out.println("Enter ID of Matrix: ");
                    int ch= Reader.nextint();
                    System.out.println("The selected matrix is of the following types: ");
                    allmatrices.get(ch).getMatrixTypes();
                }






                else if(choice==5){
                    System.out.println("The input matrices are: ");
                    for(int i=0; i<allmatrices.size();i++){
                        System.out.print(i + "." +"\n" );
                        allmatrices.get(i).printmatrix();
                        System.out.println();
                    }
                    System.out.println("Enter ID of Matrix 1: ");
                    int ch= Reader.nextint();
                    System.out.println("Enter ID of Matrix 2: ");
                    int ch2= Reader.nextint();
                    matrix m1=allmatrices.get(ch);
                    matrix m2=allmatrices.get(ch2);
                   
                        System.out.println("""
                        1.Addition
                        2.Subtraction
                        3.Multiplication
                        4.Division
                        """);
                        System.out.println("Enter choice ");
                        int ch3= Reader.nextint();
                        int[][] res= new int[m1.rows][m1.cols];
                        System.out.println("Ans = ");
                        if (ch3==1){
                            if(m1.rows==m2.rows&& m1.cols==m2.cols){
                                for(int i=0;i<m1.rows;i++){
                                    for(int j=0; j<m1.cols;j++){
                                        res[i][j]=m1.matrix[i][j]+ m2.matrix[i][j];
                                    }
                                }
                                for(int i=0; i<m1.rows;i++){
                                    for(int j=0;j<m1.cols;j++){ 
                                        System.out.print(res[i][j]+ " ");
                                    }
                                    System.out.println();
                                }
                            }
                            else{
                                System.out.println("Rows and columns mismatch! ");
                            }
                            
                        }
                        else if(ch3==2){
                            if(m1.rows==m2.rows&& m1.cols==m2.cols){
                                for(int i=0;i<m1.rows;i++){
                                    for(int j=0; j<m1.cols;j++){
                                        res[i][j]=m1.matrix[i][j]- m2.matrix[i][j];
                                    }
                                }
                                for(int i=0; i<m1.rows;i++){
                                    for(int j=0;j<m1.cols;j++){ 
                                        System.out.print(res[i][j]+ " ");
                                    }
                                    System.out.println();
                                }
                            }
                            else{
                                System.out.println("Rows and columns mismatch! ");
                            }
                            
                        }
                        
                        else if(ch3==3){
                            int[][] resmul= new int[m1.rows][m2.cols];
                            if(m1.rows!=m2.cols){
                                System.out.println("Matrix multiplication is not possible, rows and columns mismatch!");
                            }
                            else{
                                if(m1.matrixtypes.contains("Null matrix") || m2.matrixtypes.contains("Null matrix")){
                                    System.out.println("Multiplication with null matrix! ");
                                    for(int i=0; i<m1.rows;i++){
                                        for(int j=0;j<m2.cols;j++){ 
                                            System.out.print(0+ " ");
                                        }
                                        System.out.println();
                                    }
                                }
                                else if(m1.matrixtypes.contains("Identity matrix")){
                                    System.out.println("First matrix identity matrix! ");
                                    m2.printmatrix();
                                }
                                else if(m2.matrixtypes.contains("Identity matrix")){
                                    System.out.println("Second matrix identity matrix! ");
                                    m1.printmatrix();
                                }
                                else{
                                    for(int i=0;i<m1.rows;i++){
                                        for(int j=0; j<m2.cols;j++){
                                            for(int k=0; k<m2.rows;k++){
                                                resmul[i][j]+=m1.matrix[i][k]*m2.matrix[k][j];
                                                // System.out.println("k");
                                            }
                                        }
                                    }
                                    for(int i=0; i<m1.rows;i++){
                                        for(int j=0;j<m2.cols;j++){ 
                                            System.out.print(resmul[i][j]+ " ");
                                        }
                                        System.out.println();
                                    }
                                }
                                
                            }
                        }
                        else if(ch3==4){
                            float[][] resdiv= new float[m1.rows][m2.cols];
                            float[][] m2inv= m2.getinverse();
                            if(m2.getdeterminant()==0){
                                System.out.println("Matrix 2 is a singular matrix! Division not possible.");
                                continue;
                            }
                            else{
                                for(int i=0;i<m1.rows;i++){
                                    for(int j=0; j<m2inv[i].length;j++){
                                        for(int k=0; k<m2inv.length;k++){
                                            resdiv[i][j]+=(float)m1.matrix[i][k]*m2inv[k][j];
                                        }
                                    }
                                }
                                for(int i=0; i<m1.rows;i++){
                                    for(int j=0;j<m2.cols;j++){ 
                                        System.out.print(resdiv[i][j]+ " ");
                                    }
                                    System.out.println();
                                }
                                // resdiv= m1.matrix * m2.getinverse();
                            }
                            
                        }
                    
                    
                    
                }
                else if(choice==6){
                    System.out.println("The input matrices are: ");
                    for(int i=0; i<allmatrices.size();i++){
                        System.out.print(i + "." +"\n" );
                        allmatrices.get(i).printmatrix();
                        System.out.println();
                    }
                    System.out.println("Enter ID of Matrix 1: ");
                    int ch= Reader.nextint();
                    System.out.println("Enter ID of Matrix 2: ");
                    int ch2= Reader.nextint();
                    matrix m1=allmatrices.get(ch);
                    matrix m2=allmatrices.get(ch2);
                    if (sameorder(m1,m2)==true){
                        System.out.println("""
                        1.Addition
                        2.Subtraction
                        3.Multiplication
                        4.Division
                        """);
                        System.out.println("Enter choice ");
                        int ch3= Reader.nextint();
                        int[][] res= new int[m1.rows][m1.cols];
                        System.out.println("Ans = ");
                        if (ch3==1){
                            for(int i=0;i<m1.rows;i++){
                                for(int j=0; j<m1.cols;j++){
                                    res[i][j]=m1.matrix[i][j]+ m2.matrix[i][j];
                                }
                            }
                            for(int i=0; i<m1.rows;i++){
                                for(int j=0;j<m1.cols;j++){ 
                                    System.out.print(res[i][j]+ " ");
                                }
                                System.out.println();
                            }
                        }
                        else if(ch3==2){
                            for(int i=0;i<m1.rows;i++){
                                for(int j=0; j<m1.cols;j++){
                                    res[i][j]=m1.matrix[i][j]- m2.matrix[i][j];
                                }
                            }
                            for(int i=0; i<m1.rows;i++){
                                for(int j=0;j<m1.cols;j++){ 
                                    System.out.print(res[i][j]+ " ");
                                }
                                System.out.println();
                            }
                        }
                        else if(ch3==3){
                            for(int i=0;i<m1.rows;i++){
                                for(int j=0; j<m1.cols;j++){
                                    res[i][j]=m1.matrix[i][j]* m2.matrix[i][j];
                                }
                            }
                            for(int i=0; i<m1.rows;i++){
                                for(int j=0;j<m1.cols;j++){ 
                                    System.out.print(res[i][j]+ " ");
                                }
                                System.out.println();
                            }
                        }
                        else if(ch3==4){
                            Float[][] resdiv= new Float[m1.rows][m1.cols];
                            for(int i=0;i<m1.rows;i++){
                                for(int j=0; j<m1.cols;j++){
                                    if(m2.matrix[i][j]!=0){
                                        resdiv[i][j]=(float)m1.matrix[i][j]/ m2.matrix[i][j];
                                    }
                                    // else{
                                    //     System.out.println("Zero division error in one/more elements");
                                    //     continue;
                                    // }
                                }
                            }
                            int nullc=0;
                            for(int i=0; i<m1.rows;i++){
                                for(int j=0;j<m1.cols;j++){ 
                                    if(resdiv[i][j]==null){
                                        nullc+=1;                                       
                                    }
                                }
                            }
                            if(nullc>0){
                                System.out.println("Zero division error in one/more elements");
                                continue;
                            }
                            else{
                                for(int i=0; i<m1.rows;i++){
                                    for(int j=0;j<m1.cols;j++){ 
                                        System.out.print(res[i][j]+ " ");
                                    }
                                    System.out.println();
                                }
                            } 
                        }
                    }
                    else{
                        System.out.println("Please choose matrices of the same order");
                    }
                }





                else if(choice==7){
                    System.out.println("The input matrices are: ");
                    for(int i=0; i<allmatrices.size();i++){
                        System.out.print(i + "." +"\n" );
                        allmatrices.get(i).printmatrix();
                        System.out.println();
                    }
                    System.out.println("Enter ID of matrix: ");
                    int ch= Reader.nextint();
                    if(allmatrices.get(ch).matrixtypes.contains("Null matrix") || allmatrices.get(ch).matrixtypes.contains("Ones matrix") || allmatrices.get(ch).matrixtypes.contains("Singleton matrix") || allmatrices.get(ch).matrixtypes.contains("Identity matrix") || allmatrices.get(ch).matrixtypes.contains("Symmetric matrix")){
                        allmatrices.get(ch).printmatrix();
                    }
                    else{
                        allmatrices.get(ch).transpose();
                    }
                    
                }







                else if(choice==8){
                    System.out.println("The input matrices are: ");
                    for(int i=0; i<allmatrices.size();i++){
                        System.out.print(i + "." +"\n" );
                        allmatrices.get(i).printmatrix();
                        System.out.println();
                    }
                    System.out.println("Enter ID of matrix: ");
                    int ch= Reader.nextint();
                    if(allmatrices.get(ch).getdeterminant()==0){
                        System.out.println("Matrix is singular, inverse not possible! ");
                    }
                    else{System.out.println("Inverted matrix: ");
                        float[][] inv= allmatrices.get(ch).getinverse();
                        for(int i=0;i<allmatrices.get(ch).rows;i++){
                            for(int j=0;j<allmatrices.get(ch).cols;j++){
                                System.out.print(inv[i][j]+ " ");
                            }
                            System.out.println();
                        }
                    }
                    
                }







                else if(choice==9){
                    System.out.println("The input matrices are: ");
                    for(int i=0; i<allmatrices.size();i++){
                        System.out.print(i + "." +"\n" );
                        allmatrices.get(i).printmatrix();
                        System.out.println();
                    }
                    System.out.println("Enter ID of matrix: ");
                    int ch= Reader.nextint();
                    System.out.println("""
                    1.Row-wise mean
                    2.Column-wise mean
                    """);
                    System.out.println("Option: ");
                    int ch1= Reader.nextint();                   
                    int r= allmatrices.get(ch).rows;
                    int c= allmatrices.get(ch).cols;
                    if (ch1==1){
                        float[] avgra=new float[r];
                        float avgr=0;
                        for(int i=0;i<r;i++){
                            for(int j=0;j<c;j++){
                                avgr+=(float)allmatrices.get(ch).matrix[i][j]/c;
                            }
                            avgra[i]=avgr;
                            avgr=0;
                        }
                        System.out.println("Row-wise means: ");
                        for(int i=0;i<r;i++){
                            System.out.println(avgra[i]);
                        }
                        System.out.println();
                    }
                    else if(ch1==2){
                        float[] avgca=new float[c];
                        float avgc=0;
                        for(int i=0;i<c;i++){
                            for(int j=0;j<r;j++){
                                avgc+=(float)allmatrices.get(ch).matrix[j][i]/r;
                            }
                            avgca[i]=avgc;
                            avgc=0;
                        }
                        System.out.println("Column-wise means: ");
                        for(int i=0;i<c;i++){
                            System.out.println(avgca[i]+ " ");
                        }
                        System.out.println();                   
                    }
                    else{
                        continue;
                    }
                }






                else if(choice==10){
                    System.out.println("The input matrices are: ");
                    for(int i=0; i<allmatrices.size();i++){
                        System.out.print(i + "." +"\n" );
                        allmatrices.get(i).printmatrix();
                        System.out.println();
                    }
                    System.out.println("Enter ID of matrix : ");
                    int ch= Reader.nextint();
                    if(allmatrices.get(ch).matrixtypes.contains("Singular matrix")){
                        System.out.println("Determinant of this matrix is: 0 (Singular matrix)");
                    }
                    else if(allmatrices.get(ch).matrixtypes.contains("Identity matrix")){
                        System.out.println("Determinant of this matrix is: 1 (Identity matrix)");
                    }
                    else if(allmatrices.get(ch).matrixtypes.contains("Diagonal matrix") || allmatrices.get(ch).matrixtypes.contains("Scalar matrix")  ){
                        int det=1;
                        for(int i=0;i<allmatrices.get(ch).rows;i++){
                            
                                det*=allmatrices.get(ch).matrix[i][i];
                            
                        }
                        System.out.println("Determinant of this matrix is: "+  det);
                    }
                    else{
                        int det= allmatrices.get(ch).getdeterminant();
                        System.out.println("Determinant of this matrix is: "+  det);
                    }
                    
                    
                }









                else if(choice==11){
                    System.out.println("Use singleton matrix in matrix operations. Do you allow using singleton matrices as a scalar value?");
                    // String inp=Reader.next();
                    System.out.println("Yes");
                    // if(inp=="yes\n"|| inp=="Yes\n"){
                        System.out.println("The input matrices are: ");
                        for(int i=0; i<allmatrices.size();i++){
                            System.out.print(i + "." +"\n" );
                            allmatrices.get(i).printmatrix();
                            System.out.println();
                        }
                        System.out.println("Enter ID of matrix A: ");
                        int ch= Reader.nextint();
                        System.out.println("Choose a singleton matrix now. ");
                        for(int i=0; i<allmatrices.size();i++){
                            if(allmatrices.get(i).matrixtypes.contains("Singular matrix")){
                                System.out.print(i + "." +"\n" );
                                allmatrices.get(i).printmatrix();
                                System.out.println();
                            }          
                        }
                        System.out.println("Enter ID of singleton matrix B: ");
                        int ch2= Reader.nextint();
                        System.out.println();
                        int r=allmatrices.get(ch).rows;
                        int c=allmatrices.get(ch).cols;
                        int[][] res= new int[r][c];
                        System.out.println("A*(scalar of singleton matrix B): ");
                        System.out.println();
                        int scalar= allmatrices.get(ch2).getScalar();
                        for(int i=0;i<r;i++){
                            for(int j=0; j<c;j++){
                                res[i][j]=allmatrices.get(ch).matrix[i][j]* scalar;
                            }
                        }
                        for(int i=0; i<r;i++){
                            for(int j=0;j<c;j++){ 
                                System.out.print(res[i][j]+ " ");
                            }
                            System.out.println();
                        }
                    // }
                }





                else if(choice==12){
                    System.out.println("The input matrices are: ");
                    for(int i=0; i<allmatrices.size();i++){
                        System.out.print(i + "." +"\n" );
                        allmatrices.get(i).printmatrix();
                        System.out.println();
                    }
                    System.out.println("Enter ID of matrix A: ");
                    int ch= Reader.nextint();
                    System.out.println("A + A^T matrix: ");
                    if(allmatrices.get(ch).matrixtypes.contains("Rectangular matrix")){
                        System.out.println("Element wise addition is not possible (A is not a square matrix)");
                    }
                    else{
                        if(allmatrices.get(ch).matrixtypes.contains("Null matrix")){
                            allmatrices.get(ch).printmatrix();
                        }
                        else if(allmatrices.get(ch).matrixtypes.contains("Skew Symmetric matrix")){
                            for(int i=0;i<allmatrices.get(ch).rows;i++){
                                for(int j=0;j<allmatrices.get(ch).cols;j++){
                                    System.out.print(0 + " ");
                                }
                                System.out.println();
                            }
                        }
                        else{
                            int r=allmatrices.get(ch).rows;
                            // int c=allmatrices.get(ch).cols;
                            int[][] mx= allmatrices.get(ch).matrix;
                            int[][] mxt= allmatrices.get(ch).gettranspose();
                            int[][] res=new int[r][r];
                            for(int i=0;i<r;i++){
                                for(int j=0;j<r;j++){
                                    res[i][j]=mx[i][j]+mxt[i][j];
                                }
                            }
                            for(int i=0;i<r;i++){
                                for(int j=0;j<r;j++){
                                    System.out.print(res[i][j]+ " ");
                                    // res[i][j]=mx[i][j]+mxt[i][j];
                                }
                                System.out.println();
                            }
                        }
                    }
                    
                }








                else if(choice==13){
                    System.out.println("Choose a 2x2 square matrix from from the available matrices: ");
                    System.out.println();
                    for(int i=0; i<allmatrices.size();i++){
                        if(allmatrices.get(i).matrixtypes.contains("Square matrix")){
                            System.out.print(i + "." +"\n" );
                            allmatrices.get(i).printmatrix();
                            System.out.println();
                        }          
                    }
                    System.out.println("Enter ID of square matrix A: ");
                    int ch= Reader.nextint();
                    System.out.println();
                    try{
                        matrix sqm=allmatrices.get(ch);
                        // matrix idm= new idmatrix(2);
                        int a= 1;
                        int b= -(sqm.matrix[1][1] + sqm.matrix[0][0]);
                        int c= (sqm.matrix[1][1]*sqm.matrix[0][0]-sqm.matrix[0][1]*sqm.matrix[1][0] );
                        double D=b*b-4*a*c;
                        double lamb1=(-b + Math.sqrt(D))/2*a;
                        double lamb2=(-b - Math.sqrt(D))/2*a;
                        System.out.println("Eigen values of selected matrix are: "+"\n"+ String.format("%.4f",lamb1)  +"\n "+ String.format("%.4f",lamb2));
                        System.out.println();
                        // int[][] lam1mx=sqm.matrix;
                        double[][] lam1mx=new double[2][2];
                        for(int i=0;i<2;i++){
                            for(int j=0;j<2;j++){
                                lam1mx[i][j]=sqm.matrix[i][j];
                            }
                        }
                        for(int i=0;i<2;i++){
                            // System.out.println((double)sqm.matrix[i][i]+ "-" +lamb1 );
                            lam1mx[i][i]=(lam1mx[i][i] - lamb1);
                        }
                        double[][] lam2mx=new double[2][2];
                        for(int i=0;i<2;i++){
                            for(int j=0;j<2;j++){
                                lam2mx[i][j]=sqm.matrix[i][j];
                            }
                        }
                        for(int i=0;i<2;i++){
                            lam2mx[i][i]=lam2mx[i][i]-lamb2;
                        }
                        double x1 =- ((double)lam1mx[0][1]/lam1mx[0][0]); 
                        double x2=1;
                        // for(int i=0;i<2;i++){
                        //     for(int j=0;j<2;j++){
                        //         System.out.print(lam1mx[i][j] + " ");
                        //     }
                        //     System.out.println();
                        // }
                        double[] lam1mxev= {x1,x2};

                        double x3=- ((double)lam2mx[0][1]/lam2mx[0][0]);
                        double x4=1;

                        double[] lam2mxev= {x3,x4};

                        System.out.println("Eigen vectors of the selected matrix are: "+ "\n"+ String.format("%.4f",lam1mxev[0])+"\n"+String.format("%.4f",lam1mxev[1]));
                        System.out.println();
                        System.out.println("    and");
                        System.out.println();
                        System.out.println(String.format("%.4f",lam2mxev[0])+"\n"+String.format("%.4f",lam2mxev[1]));
                    }
                    catch(ArithmeticException e){
                        System.out.println("Zero division error, can't compute eigen values and vectors! ");
                    }
                    

                }






                else if(choice==14){
                    System.out.println("""
                    Solve a set of linear equation.
                    Choose a square matrix from the available matrices: 
                    """);
                    System.out.println();
                    for(int i=0; i<allmatrices.size();i++){
                        if(allmatrices.get(i).matrixtypes.contains("Square matrix")){
                            System.out.print(i + "." +"\n" );
                            allmatrices.get(i).printmatrix();
                            System.out.println();
                        }          
                    }
                    System.out.println("Enter ID of square matrix A: ");
                    int ch= Reader.nextint();
                    matrix sqm=allmatrices.get(ch);
                    System.out.println("Choose a column matrix from the available matrices: ");
                    System.out.println();
                    for(int i=0; i<allmatrices.size();i++){
                        if(allmatrices.get(i).matrixtypes.contains("Column matrix") && allmatrices.get(i).rows==sqm.rows){
                            System.out.print(i + "." +"\n" );
                            allmatrices.get(i).printmatrix();
                            System.out.println();
                        }          
                    }
                    System.out.println("Enter ID of column matrix B: ");
                    int ch2= Reader.nextint();
                    System.out.println();
                    System.out.println("The required solution is: ");
                    
                    matrix colm=allmatrices.get(ch2);
                    float[][] sqminv= sqm.getinverse();
                    float[][] res=new float[sqm.rows][1];

                    for(int i=0;i<sqm.rows;i++){
                        for(int j=0; j<1;j++){
                            for(int k=0; k<sqm.rows;k++){
                                res[i][j]+=(float)sqminv[i][k]*colm.matrix[k][j];
                            }
                        }
                    }
                    for(int i=0; i<sqm.rows;i++){
                        for(int j=0;j<1;j++){ 
                            System.out.print(res[i][j]+ " ");
                        }
                        System.out.println();
                    }
                    // resdiv= m1.matrix * m2.getinverse();
                }
                





                else if(choice==15){
                    System.out.println();
                    System.out.println("All the matrix types: ");
                    System.out.println("""
                    1.  Rectangular matrix
                    2.  Row matrix
                    3.  Column matrix
                    4.  Square matrix
                    5.  Symmetric matrix
                    6.  Skew Symmetric matrix
                    7.  Upper Triangular matrix
                    8.  Lower Triangular matrix
                    9.  Singular matrix
                    10.  Diagonal matrix
                    11.  Scalar matrix
                    12.  Identity matrix
                    13.  Singleton matrix
                    14.  Ones matrix
                    15.  Null matrix                  
                    """);
                    String[] types= {"Rectangular matrix","Row matrix","Column matrix","Square matrix","Symmetric matrix","Skew Symmetric matrix","Upper Triangular matrix","Lower Triangular matrix","Singular matrix","Diagonal matrix","Scalar matrix","Identity matrix","Singleton matrix","Ones matrix","Null matrix"};
                    System.out.println();
                    System.out.println("Enter the ID of type of matrix you want to search for : ");
                    int inp=Reader.nextint();
                    System.out.println("All the matrices of type "+ types[inp-1]+ " are:");
                    for(int i=0;i<allmatrices.size();i++){
                        if(allmatrices.get(i).matrixtypes.contains(types[inp-1])){
                            System.out.print(i + "." +"\n" );
                            allmatrices.get(i).printmatrix();
                            System.out.println();
                        }
                    }
                }
                else{
                    System.out.println("Exiting..");
                    break;
                }
            }
            catch(Error e){
                System.out.println("Enter a valid option!");
                
            }
        }
        
    }
}
