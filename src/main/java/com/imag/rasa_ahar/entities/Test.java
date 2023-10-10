package com.imag.rasa_ahar.entities;

import java.util.*;

public class Test {
    public static void main(String[] args) {

    }
    public int evalRPN(String[] tokens) {
        int i,j,a,b,c;
        Stack<Integer> s = new Stack<>();
        for(i=0;i<tokens.length;i++){
            if(tokens[i].length()==1){
                char ch;
                ch = tokens[i].charAt(0);
                if(Character.isDigit(ch)){
                    s.push(Character.getNumericValue(ch));
                }
                else{
                    a = s.pop();
                    b = s.pop();
                    if(ch=='+'){
                        c = b+a;
                    }
                    else if(ch=='-'){
                        c=b-a;
                    }
                    else if(ch=='/'){
                        c=b/a;
                    }
                    else{
                        c=b*a;
                    }
                }
            }
            else{
                Integer num = Integer.valueOf(tokens[i]);
            }

        }
        return s.pop();
    }
}
