class HelloWorld4 {
    static void fun(){
        int x = 4;
    }

    static void function(){
        int x = 3;
    }
    public static void main(String[] args) {
        int x = 5;
        fun();
        if (x == 5 || x == 6) {
            System.out.println("if");
            System.out.println("if");
        }


         else if (x == 7)
            System.out.println("else if");

        else
            System.out.println("else");

        for (int i = 8; i < 3; i++) {
            System.out.println("for");
        }


    }
}