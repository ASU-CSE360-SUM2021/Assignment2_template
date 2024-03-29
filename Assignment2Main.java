import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Vector;

public class Main {
  public static void main(String[] args) {
    /*****
     * Generate 10 random numbers
     * 5 of the numbers are between [0, 10)
     * 5 of the numbers are between (-10, 0]
    *****/
    NumberArray arr = new NumberArray();

    /***** Create JFrame *****/
    JFrame frame = new JFrame();
    frame.setSize(400, 500);
    frame.setLayout(new GridLayout(1,1));

    /***** Add tabs to the frame *****/
    JTabbedPane tabs = new JTabbedPane();
    String[] columnNames = {"Positive Numbers", "Negative Numbers"};

    /***** Panel for iterator design pattern *****/
    /***
     * TODO: Create a NumberIterator class to go through arr to create an Object[][]
     * that you can insert into the JTable constructor below
     */
    NumberIterator numIter = new NumberIterator( arr );
    Object[][] iterOutput = numIter.createTableData();

    JTable iteratorTable = new JTable( iterOutput, columnNames );

    JScrollPane iteratorPanel = new JScrollPane(iteratorTable);
    iteratorTable.setFillsViewportHeight(true);
    
    tabs.addTab("Iterator", iteratorPanel);

    /***** Panel for facade design pattern *****/
    NumberFacade numFac = new NumberFacade( arr );
    numFac.addNumbers();
    numFac.subtractNumbers();
    numFac.multipleNumbers();

    JPanel facadePanel = new JPanel();
    String sampleText = "Add all positive numbers:" + arr.positiveNumbers[1];
    JLabel text1 = new JLabel(sampleText);
    JLabel text2 = new JLabel("Add all negatvie numbers: something" );
    facadePanel.add(text1);
    facadePanel.add(text2);
    
    tabs.addTab("Facade", facadePanel);

    /***** Panel for observer design pattern *****/
    Observer numObs = new Observer( arr );
    numFac.setObserver( numObs ); // Set the observer
    numFac.addNumbers(); // will notify the observer after finishing the calculations
    numFac.subtractNumbers(); // will notify the observer after finishing the calculations
    numFac.multiplyNumbers(); // will notify the observer after finishing the calculations

    /** Iterate through the modified arrays and display the results **/
    numIter = new NumberIterator( numObs.allNumbers );
    Object[][] observerOutput = numIter.createTableData();

    JTable observerTable = new JTable(3, 2);

    JScrollPane observerPanel = new JScrollPane(observerTable);
    observerTable.setFillsViewportHeight(true);
    
    tabs.addTab("Observer", observerPanel);

    /***** Panel for decorator design pattern *****/
    JTable decoratorTable1 = new JTable(3, 2);
    JTable decoratorTable2 = new JTable(3, 2);
    JTable decoratorTable3 = new JTable(3, 2);

    JPanel allTables = new JPanel(new GridLayout(6, 1));
    
    allTables.add(new JLabel("AddOp's table"));
    JScrollPane addTable = new JScrollPane(decoratorTable1);
    addTable.setPreferredSize( new Dimension( 300, 100 )) ;
    allTables.add(addTable);
    
    allTables.add(new JLabel("SubtractOp's table"));
    JScrollPane subTable = new JScrollPane(decoratorTable2);
    subTable.setPreferredSize( new Dimension( 300, 100 )) ;
    allTables.add(subTable);
    
    allTables.add(new JLabel("MuliplierOp's table"));
    JScrollPane mulTable = new JScrollPane(decoratorTable3);
    mulTable.setPreferredSize( new Dimension( 300, 100 )) ;
    allTables.add(mulTable);
    
    JScrollPane decoratorPanel = new JScrollPane(allTables);

    tabs.addTab("Decorator", decoratorPanel);

    frame.add(tabs, BorderLayout.CENTER);
    frame.setVisible(true);
  }
}
