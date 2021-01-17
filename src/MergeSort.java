import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * A program that outputs to a text file information that, when read by an AnimationReader, can be
 * represented as an animation of MergeSort.
 */
public class MergeSort {

  private static List<int[]> mergePhases;

  /**
   * The main methods. The user should input two arguments: first a number (5 or above) to represent
   * the amount of shapes being sorted, and second a file to output the textual representation of
   * the animation to.
   *
   * @param args the user's input: the amount of shapes being sorted and the output file.
   */
  public static void main(String[] args) {
    try {
      Integer.parseInt(args[0]);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(new JFrame(),
          "Number could not be parsed.",
          "error",
          JOptionPane.ERROR_MESSAGE);
    }
    if (Integer.parseInt(args[0]) < 5) {
      JOptionPane.showMessageDialog(new JFrame(),
          "MergeSort must take 5 or more shapes.",
          "error",
          JOptionPane.ERROR_MESSAGE);
    }
    int shapeCount = Integer.parseInt(args[0]);
    FileWriter ap = null;
    try {
      ap = new FileWriter(args[1]);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(new JFrame(),
          "File could not be parsed.",
          "error",
          JOptionPane.ERROR_MESSAGE);
    }
    printMergeSort(shapeCount, ap);
  }

  /**
   * Appends the text information for the MergeSort animation to the appendable.
   *
   * @param shapeCount the amount of shapes to be sorted.
   * @param ap         the file to write the text to.
   */
  private static void printMergeSort(int shapeCount, FileWriter ap) {
    mergePhases = new ArrayList<>();
    appendToOutput(ap, new StringBuilder("canvas 0 0 " + ((15 * shapeCount) + 5) + " 500\n"));
    int[] heights = new int[shapeCount + 1];
    String[] shapeNames = new String[shapeCount + 1];
    for (int i = 0; i <= shapeCount; i++) {
      int height = (int) (Math.random() * 500) + 1;
      heights[i] = height;
      shapeNames[i] = "r" + i;
      appendToOutput(ap, new StringBuilder("shape " + shapeNames[i] + " rectangle\n"));
    }
    appendToOutput(ap, makeInitialMotions(0, 25, heights, shapeNames));
    int[] sortedHeights = mergeSort(heights.clone());
    appendToOutput(ap, makeSortedMotions(25, 75, heights, mergePhases.get(0), shapeNames));
    int t = 75;
    for (int i = 0; i < mergePhases.size() - 1; i++) {
      appendToOutput(ap, makeSortedMotions(t, (t + 50), mergePhases.get(i), mergePhases.get(i + 1),
          shapeNames));
      t += 50;
    }
    appendToOutput(ap, makeSortedMotions(t, (t + 50), mergePhases.get(mergePhases.size() - 1),
        sortedHeights, shapeNames));
    appendToOutput(ap, makeInitialMotions((t + 50), (t + 75), sortedHeights, shapeNames));
    try {
      ap.close();
    } catch (IOException e) {
      JOptionPane.showMessageDialog(new JFrame(),
          "Output could not be closed.",
          "error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Sorts the heights of the shapes in the animation using mergesort.
   *
   * @param heights the array of heights of the shapes
   * @return the sorted array.
   */
  private static int[] mergeSort(int[] heights) {
    if (heights.length == 1) {
      return heights;
    }
    int m = heights.length / 2;
    int[] l = new int[m];
    int[] r = new int[heights.length - m];
    int t = 0;
    for (int i = 0; i < heights.length; i++) {
      if (i < m) {
        l[i] = heights[i];
      } else {
        r[t] = heights[i];
        t++;
      }
    }
    mergePhases.add(heights);
    mergeSort(l);
    mergeSort(r);
    merge(heights, l, r);
    return heights;
  }

  /**
   * Merges the left and right sides of an array.
   *
   * @param heights the array being sorted.
   * @param l       the left side of the array.
   * @param r       the right side of the array.
   */
  private static void merge(int[] heights, int[] l, int[] r) {
    int j = 0;
    int k = 0;
    for (int i = 0; i < (l.length + r.length); i++) {
      if (j >= l.length) {
        heights[i] = r[k];
        k++;
      } else if (k >= r.length) {
        heights[i] = l[j];
        j++;
      } else if (l[j] <= r[k]) {
        heights[i] = l[j];
        j++;
      } else {
        heights[i] = r[k];
        k++;
      }
    }
  }

  /**
   * Creates information for static motions (used to show the initial list and the sorted list).
   *
   * @param t1         the first tick in the motion.
   * @param t2         the second tick in the motion.
   * @param heights    the heights of the shapes being represented.
   * @param shapeNames the names of the shapes being represented.
   * @return a string of the motions created to be added to the appendable.
   */
  private static StringBuilder makeInitialMotions(int t1, int t2, int[] heights,
      String[] shapeNames) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < heights.length; i++) {
      int xCoord = (15 * i) + 5;
      result.append("motion " + shapeNames[i] + " " + t1 + " " + xCoord + " 5 10 " + heights[i] +
          " 0 0 0 " + t2 + " " + xCoord + " 5 10 " + heights[i] + " 0 0 0\n");
    }
    return result;
  }

  /**
   * Creates information for transitional motions (used to show the sorting process).
   *
   * @param t1            the first tick in the motion.
   * @param t2            the second tick in the motion.
   * @param heights       the heights of the initial shapes being represented.
   * @param sortedHeights the heights of the final shapes being represented.
   * @param shapeNames    the names of the shapes being represented.
   * @return a string of the motions created to be added to the appendable.
   */
  private static StringBuilder makeSortedMotions(int t1, int t2, int[] heights, int[] sortedHeights,
      String[] shapeNames) {
    StringBuilder result = new StringBuilder();
    int length;
    if (heights.length > sortedHeights.length) {
      length = sortedHeights.length;
    } else {
      length = heights.length;
    }
    for (int i = 0; i < length; i++) {
      int xCoord = (15 * i) + 5;
      result.append("motion " + shapeNames[i] + " " + t1 + " " + xCoord + " 5 10 " + heights[i] +
          " 0 0 0 " + t2 + " " + xCoord + " 5 10 " + sortedHeights[i] + " 0 0 0\n");
    }
    return result;
  }

  /**
   * Appends a given StringBuilder to the appendable. Uses a try catch and creates a message dialog
   * if the StringBuilder cannot be appended.
   *
   * @param ap the appendable to transmit the text to.
   * @param s  the text being appended to the appendable.
   */
  private static void appendToOutput(Appendable ap, StringBuilder s) {
    try {
      ap.append(s);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(new JFrame(),
          "Error printing to file.",
          "error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

}
