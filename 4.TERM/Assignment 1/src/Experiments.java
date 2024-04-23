import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Experiments {
    public static void SortingCalculation(String filename){
        int[][] arrays = ReadData.GetData(filename);
        String[] sizes = {"500" , "1k" , "2k", "4k" ,"8k","16k" , "32k","64k","128k","250k"};
        String[] algorithms = {"Selection Sort", "QuickSort" , "BucketSort"};
        int[][] sorted_arrays = arrays;

       
        System.out.println("******************************************\n");
        for(int i = 0 ; i < algorithms.length; i++){
            System.out.println("Calculating Time for Algorithm: " + algorithms[i] + " (Random Input)" + "\n");
            for(int j = 0 ; j < sizes.length; j++){
                System.out.print("Size : " + sizes[j] + " --> ");
                long total_time = 0;
                int[] sorted;

                for(int k = 0; k<10;k++){

                    Collections.shuffle(Arrays.asList(arrays[j]));



                    if(algorithms[i] == "Selection Sort"){
                      long startTime = System.currentTimeMillis();
                      sorted = Algos.SelectionSort(arrays[j]);
                      long endTime = System.currentTimeMillis();
                      long duration = endTime - startTime;
                      total_time+=duration;
                      System.out.print(duration +"  ");
                      sorted_arrays[j] = sorted;
                    }

                    if(algorithms[i] == "QuickSort"){
                        long startTime = System.currentTimeMillis();
                        sorted = Algos.QuickSort(arrays[j]);
                        long endTime = System.currentTimeMillis();
                        long duration = endTime - startTime;
                        total_time+=duration;
                        System.out.print(duration +"  ");
                        sorted_arrays[j] = sorted;
                      }

                      if(algorithms[i] == "BucketSort"){
                        long startTime = System.currentTimeMillis();
                        sorted = Algos.BucketSort(arrays[j]);
                        long endTime = System.currentTimeMillis();
                        long duration = endTime - startTime;
                        total_time+=duration;
                        System.out.print(duration +"  ");
                        sorted_arrays[j] = sorted;
                        
                      }

                      

  
                }
                long avg = total_time/10;
                System.out.print(" Average Time: " + avg + " Miliseconds");
                
                System.out.println("\n");

            }
        }

        System.out.println("******************************************\n");

        for(int i = 0 ; i < algorithms.length; i++){
            System.out.println("Calculating Time for Algorithm: " + algorithms[i] + " (Sorted Input)" + "\n");
            for(int j = 0 ; j < sizes.length; j++){
                System.out.print("Size : " + sizes[j] + " --> ");
                long total_time = 0;
                

                for(int k = 0; k<10;k++){


                    if(algorithms[i] == "Selection Sort"){
                      long startTime = System.currentTimeMillis();
                      Algos.SelectionSort(sorted_arrays[j]);
                      long endTime = System.currentTimeMillis();
                      long duration = endTime - startTime;
                      total_time+=duration;
                      System.out.print(duration +"  ");
                      
                    }

                    if(algorithms[i] == "QuickSort"){
                        long startTime = System.currentTimeMillis();
                        Algos.QuickSort(sorted_arrays[j]);
                        long endTime = System.currentTimeMillis();
                        long duration = endTime - startTime;
                        total_time+=duration;
                        System.out.print(duration +"  ");
                        
                      }

                      if(algorithms[i] == "BucketSort"){
                        long startTime = System.currentTimeMillis();
                        Algos.BucketSort(sorted_arrays[j]);
                        long endTime = System.currentTimeMillis();
                        long duration = endTime - startTime;
                        total_time+=duration;
                        System.out.print(duration +"  ");
                        
                        
                      }

                      

  
                }
                long avg = total_time/10;
                System.out.print(" Average Time: " + avg + " Miliseconds");
                
                System.out.println("\n");

            }
        }

        System.out.println("******************************************\n");
        int[][] ReverseSorted = sorted_arrays;
        for (int i = 0; i < sizes.length;i++){
            for(int j = 0; j < sorted_arrays[i].length-1;j++){
                ReverseSorted[i][j] = sorted_arrays[i][sorted_arrays.length -1 -i];
            }
        }


        for(int i = 0 ; i < algorithms.length; i++){
            System.out.println("Calculating Time for Algorithm: " + algorithms[i] + " (Reversely Sorted Input)" + "\n");
            for(int j = 0 ; j < sizes.length; j++){
                System.out.print("Size : " + sizes[j] + " --> ");
                long total_time = 0;
                

                for(int k = 0; k<10;k++){
                    

                    if(algorithms[i] == "Selection Sort"){
                      long startTime = System.currentTimeMillis();
                      Algos.SelectionSort(ReverseSorted[j]);
                      long endTime = System.currentTimeMillis();
                      long duration = endTime - startTime;
                      total_time+=duration;
                      System.out.print(duration +"  ");
                      
                    }

                    if(algorithms[i] == "QuickSort"){
                        long startTime = System.currentTimeMillis();
                        Algos.QuickSort(ReverseSorted[j]);
                        long endTime = System.currentTimeMillis();
                        long duration = endTime - startTime;
                        total_time+=duration;
                        System.out.print(duration +"  ");
                        
                      }

                      if(algorithms[i] == "BucketSort"){
                        long startTime = System.currentTimeMillis();
                        Algos.BucketSort(ReverseSorted[j]);
                        long endTime = System.currentTimeMillis();
                        long duration = endTime - startTime;
                        total_time+=duration;
                        System.out.print(duration +"  ");
                        
                        
                      }

                }
                long avg = total_time/10;
                System.out.print(" Average Time: " + avg + " Miliseconds");
                
                System.out.println("\n");

            }
        }


    }

    public static void SearchingCalculation(int[][] array){
      String[] sizes = {"500" , "1k" , "2k", "4k" ,"8k","16k" , "32k","64k","128k","250k"};
      String[] algorithms = {"Linear Search (Random)","Linear Search", "Binary Search"};
      int[][] sorted_Arrays = array;
      for(int i = 0 ; i < array.length;i++){
        sorted_Arrays[i] = Algos.BucketSort(array[i]);
      }

      Random random = new Random();
      for(int i = 0 ; i < algorithms.length;i++){

        if(algorithms[i] == "Linear Search (Random)"){
          System.out.println("Calculating Time for Algorithm: " + algorithms[i] + " (Random Input)" + "\n");
          for(int j = 0 ; j < sizes.length;j++){
            System.out.print("Size : " + sizes[j] + " --> ");
            long total_time = 0;

            for(int k = 0 ; k <1000;k++){
              int randomind = random.nextInt(array[j].length);

              long startTime = System.nanoTime();
              Algos.LinearSearch(array[j],array[j][randomind]);
              long endTime = System.nanoTime();
              total_time+= endTime - startTime;
            }
            long avg = total_time / 1000;
            System.out.print(" Average Time: " + avg + " Nanoseconds");
                
            System.out.println("\n");
          }
        }

        else if(algorithms[i] == "Linear Search(Sorted)"){
          System.out.println("Calculating Time for Algorithm: " + algorithms[i] + " (Sorted Input)" + "\n");
          for(int j = 0 ; j < sizes.length;j++){
            System.out.print("Size : " + sizes[j] + " --> ");
            long total_time = 0;

            for(int k = 0 ; k <1000;k++){
              int randomind = random.nextInt(sorted_Arrays[j].length);

              long startTime = System.nanoTime();
              Algos.LinearSearch(sorted_Arrays[j],sorted_Arrays[j][randomind]);
              long endTime = System.nanoTime();
              total_time+= endTime - startTime;
            }
            long avg = total_time / 1000;
            System.out.print(" Average Time: " + avg + " Nanoseconds");
                
            System.out.println("\n");
          }
        }

        else{
          System.out.println("Calculating Time for Algorithm: " + algorithms[i] + " (Sorted Input)" + "\n");
          for(int j = 0 ; j < sizes.length;j++){
            System.out.print("Size : " + sizes[j] + " --> ");
            long total_time = 0;

            for(int k = 0 ; k <1000;k++){
              int randomind = random.nextInt(sorted_Arrays[j].length);

              long startTime = System.nanoTime();
              Algos.LinearSearch(sorted_Arrays[j],sorted_Arrays[j][randomind]);
              long endTime = System.nanoTime();
              total_time+= endTime - startTime;
            }
            long avg = total_time / 1000;
            System.out.print(" Average Time: " + avg + " Nanoseconds");
                
            System.out.println("\n");
          }
        }
      }


    }

}
