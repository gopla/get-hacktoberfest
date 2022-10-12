/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stack;

/**
 *
 * @author TOSHIBA
 */
import java.util.*;
public class StackArray {
    int top;
    int arraySize;
    int tumpukan[];
   
    
    void inisialisasi(int arrSize){ // untuk menentukan besar array
        arraySize=arrSize;
        tumpukan=new int[arraySize];
        top=-1;
    }
    boolean isEmpty(){// untuk mengecek array dalam keadaan kosong ato tdk
        return top==-1;
    }
    boolean isFull(){//untuk ngecek array dlm keaadan penuh ato tdk
        return(top==arraySize-1);
    }
    void pushStack(int data){//method untuk melakukan push
       if(isFull()){// untuk memanggil method isFull apabila array sudah pnh
            System.out.println("Stack Penuh");
       tumpukan = resizing(tumpukan);}
    tumpukan[++top]=data;
     }
    int[] resizing(int[]element){// method utk mengubah ukuran array
        int[]newArr=new int[2*element.length];
        System.arraycopy(element, 0, newArr, 0, element.length);
        return newArr;
    }
    int popStack(){//methon utk pop
       int temp;
        if (isEmpty())//memanggil method is Empty utk mengecek apakah array dlm keadaan kosong ato tdk
            throw new EmptyStackException();
        temp=tumpukan[top];
        
        tumpukan[top]=0;
        top--;
        return temp;
        
    }
    void reverse(){// utk membalik isi stack
        int temp2;
        for (int i = 0; i <tumpukan.length/2; i++) {
            temp2=tumpukan[i];
            tumpukan[i]=tumpukan[tumpukan.length-i-1];
            tumpukan[tumpukan.length-i-1]=temp2;            
        }
    }
    public static void main(String[] args) {
        int inp;//utk menerima inputan user(menu)
        int data;//utk menerima inputan data dari user (saat melakukan push)
        StackArray obj = new StackArray();
        Scanner baca = new Scanner(System.in);
        
        obj.inisialisasi(3);
        do{
        System.out.println("1.push ");
        System.out.println("2.pop ");
        System.out.println("3.menampilkan stack");
        System.out.println("4.Reverse Stack");
        System.out.println("5.Tampilkan Nilai Top");
        System.out.print("Masukan Pilihan (0.Exit): ");
        inp=baca.nextInt();
        
        if (inp==1) {
            System.out.print("Masukan Data (int): ");
            data=baca.nextInt();
            obj.pushStack(data);
            
        }else if(inp==2){
            obj.popStack();
            System.out.println("PopStack Berhasil...!!! ^_^");
        }else if(inp==3){
            for (int i = 0; i < obj.tumpukan.length; i++) {
            System.out.println("tumpukan index ke-:"+i +" ="+obj.tumpukan[i]);
            }
        }else if(inp==4){
            obj.reverse();
        }else if(inp==5){
            System.out.println("Top= "+obj.tumpukan[obj.top]);
        }
        else if(inp==0){
            System.exit(0);                    
        }
        }while(true);      
    }
}
