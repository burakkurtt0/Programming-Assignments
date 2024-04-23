import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler.LegendPosition;

public class Main{
    public static void main(String[] args) {

         
        //To get values:

        int[][] Data = ReadData.GetData(args[0]);
        Experiments.SortingCalculation(args[0]);
        Experiments.SearchingCalculation(Data);
        
        


        double[] data_Size = {500,1000,2000,4000,8000,16000,32000,64000,128000,250000};

        double[] Selection_Random = {0,0,1,5,19,78,296,1056,4087,16160};
        double[] Selection_Sorted = {0,0,1,4,16,64,263,1054,4180,15960};
        double[] Selection_Reversed = {0,0,1,5,19,65,253,1029,4193,15815};

        double[] Quick_Random = {0,0,0,1,9,41,155,602,2754,10555};
        double[] Quick_Sorted = {0,0,0,3,13,48,167,623,2774,10646};
        double[] Quick_Reversed = {0,0,0,2,12,42,157,604,2726,10343};

        double[] Bucket_Random = {0,0,0,0,0,1,1,2,7,6};
        double[] Bucket_Sorted = {0,0,0,0,0,0,0,1,2,4};
        double[] Bucket_Reversed = {0,0,0,0,0,0,1,0,1,4};

        double[] Linear_Random = {1139,1608,5898,1564,5737,2503,3912,5542,5895,7071};
        double[] Linear_Sorted = {130,190,447,734,1353,1956,3895,5086,4879,5993};
        double[] Binary_Sorted = {122,355,306,557,1124,2096,3690,5031,6430,7136};

        XYChart chart = new XYChartBuilder().width(800).height(600).title("Sorting Algorithms on Random Data").build();
        chart.setXAxisTitle("Data Size");
        chart.setYAxisTitle("Time (Miliseconds)");
        XYSeries serie1 = chart.addSeries("Selection Sort", data_Size, Selection_Random);
        XYSeries serie2 = chart.addSeries("Quick Sort", data_Size, Quick_Random);
        XYSeries serie3 = chart.addSeries("Bucket Sort", data_Size, Bucket_Random);
        chart.getStyler().setLegendPosition(LegendPosition.InsideNW);

        new SwingWrapper<>(chart).displayChart();

        XYChart chart2 = new XYChartBuilder().width(800).height(600).title("Sorting Algorithms on Sorted Data").build();
        chart2.setXAxisTitle("Data Size");
        chart2.setYAxisTitle("Time (Miliseconds)");
        XYSeries serie4 = chart2.addSeries("Selection Sort", data_Size, Selection_Sorted);
        XYSeries serie5 = chart2.addSeries("Quick Sort", data_Size, Quick_Sorted);
        XYSeries serie6 = chart2.addSeries("Bucket Sort", data_Size, Bucket_Sorted);
        chart2.getStyler().setLegendPosition(LegendPosition.InsideNW);
        new SwingWrapper<>(chart2).displayChart();


        XYChart chart3 = new XYChartBuilder().width(800).height(600).title("Sorting Algorithms on Reverse-Sorted Data").build();
        chart3.setXAxisTitle("Data Size");
        chart3.setYAxisTitle("Time (Miliseconds)");
        XYSeries serie7 = chart3.addSeries("Selection Sort", data_Size, Selection_Reversed);
        XYSeries serie8 = chart3.addSeries("Quick Sort", data_Size, Quick_Reversed);
        XYSeries serie9 = chart3.addSeries("Bucket Sort", data_Size, Bucket_Reversed);
        chart3.getStyler().setLegendPosition(LegendPosition.InsideNW);
        new SwingWrapper<>(chart3).displayChart();

        XYChart chart4 = new XYChartBuilder().width(800).height(600).title("Selection Sort Performance on different input types").build();
        chart4.setXAxisTitle("Data Size");
        chart4.setYAxisTitle("Time (Miliseconds)");
        XYSeries serie10 = chart4.addSeries("Random", data_Size, Selection_Random);
        XYSeries serie11 = chart4.addSeries("Sorted", data_Size, Selection_Sorted);
        XYSeries serie12 = chart4.addSeries("Reversely Sorted", data_Size, Selection_Reversed);

        chart4.getStyler().setLegendPosition(LegendPosition.InsideNW);
        new SwingWrapper<>(chart4).displayChart();

        XYChart chart5 = new XYChartBuilder().width(800).height(600).title("Quick Sort Performance on different input types").build();
        chart5.setXAxisTitle("Data Size");
        chart5.setYAxisTitle("Time (Miliseconds)");
        XYSeries serie13 = chart5.addSeries("Random", data_Size, Quick_Random);
        XYSeries serie14 = chart5.addSeries("Sorted", data_Size, Quick_Sorted);
        XYSeries serie15 = chart5.addSeries("Reversely Sorted", data_Size, Quick_Reversed);

        chart5.getStyler().setLegendPosition(LegendPosition.InsideNW);
        new SwingWrapper<>(chart5).displayChart();


        XYChart chart6 = new XYChartBuilder().width(800).height(600).title("Bucket Sort Performance on different input types").build();
        chart6.setXAxisTitle("Data Size");
        chart6.setYAxisTitle("Time (Miliseconds)");
        XYSeries serie16 = chart6.addSeries("Random", data_Size, Bucket_Random);
        XYSeries serie17 = chart6.addSeries("Sorted", data_Size, Bucket_Sorted);
        XYSeries serie18 = chart6.addSeries("Reversely Sorted", data_Size, Bucket_Reversed);

        chart6.getStyler().setLegendPosition(LegendPosition.InsideNW);
        new SwingWrapper<>(chart6).displayChart();

        XYChart chart7 = new XYChartBuilder().width(800).height(600).title("Search Algorithm Performance on Different Input Types").build();
        chart7.setXAxisTitle("Data Size");
        chart7.setYAxisTitle("Time (NanoSeconds)");
        XYSeries serie19 = chart7.addSeries("Linear Search (Random)", data_Size, Linear_Random);
        XYSeries serie20 = chart7.addSeries("Linear Search (Sorted)", data_Size, Linear_Sorted);
        XYSeries serie21 = chart7.addSeries("Binary Search", data_Size, Binary_Sorted);

        chart7.getStyler().setLegendPosition(LegendPosition.InsideSE);
        new SwingWrapper<>(chart7).displayChart();
        


    } 
}