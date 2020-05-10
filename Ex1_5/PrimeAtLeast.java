package Ex1_5;

class PrimeAtLeast{
  
  static int FindPrime(int test, int sqrt, int n){
    int p = 0;
    while( test >= n ){
        for(int i = 2; i <= sqrt; i++){
          if( test % i == 0 ){
            i = sqrt + 1;
          }
          if(i == sqrt ){
            p = test;
            test = n - 2;
          }
        }
        test ++;
    }
    return p;
  }
  
  
  public static void main( String[] args ){
   
    int n = Integer.parseInt( args[0] );
    double m = Math.sqrt(n);
    int start = n;
    int ans = 0;
    
    if ( n < 0 ){
      return;
    }
    else if ( n == 0 || n == 1 || n == 2){
      System.out.println("2");
    }
    else{
      ans = FindPrime( start, (int)m, n );
      System.out.println(ans);
    }    
  }
}