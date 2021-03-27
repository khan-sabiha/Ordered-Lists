/**
 * Display and merge two ordered lists using the two-finger walking algorithm.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static java.lang.System.*;
public class OrderedListDemo {
    public static void main(String[] args) throws FileNotFoundException {

        // User input for reading the two lists from a text file.
        Scanner kb = new Scanner(in);
        out.print("Enter the first filename to read from: ");
        String input1 = kb.next();
        out.print("Enter the second filename to read from: ");
        String input2 = kb.next();

        File file = new File(input1);
        Scanner inputFile = new Scanner(file);
        File file2 = new File(input2);
        Scanner inputFile2 = new Scanner(file2);

        //Creating OrderedList objects to insert the two lists read from the user.
        OrderedList<String> names1 = new OrderedList<String>();
        OrderedList<String> names2 = new OrderedList<String>();

        //Inserting the two lists from the text files to the OrederedList object.
        while (inputFile.hasNext()) {
            names1.insert(inputFile.next());
        }
        while (inputFile2.hasNext()) {
            names2.insert(inputFile2.next());
        }

        //Displaying the items of the two OrderedLists.
        out.println("The Ordered Lists contain the following entries:" + "\n" + "List 1:");
        names1.enumerate();
        out.println("List 2:");
        names2.enumerate();

        out.println();

        //Merging the two Ordered lists and displaying the merged list items.
        out.println("A merged version of the two lists looks like this:");
        merge(names1,names2).enumerate();
    }

    //Method for the two-finger walking algorithm. Combines two algorithms and returns the merged list.
    public static <T extends Comparable<T>> OrderedList<T> merge(OrderedList<T> list1, OrderedList<T> list2) {

        OrderedList<T> list3 = new OrderedList<T>(); //Merged list.
        int i = 0; //Pointer for list1.
        int j = 0; //Pointer for list2.

        //Looping through the two lists and adding items to list3.
        while (i < list1.size() && j < list2.size()) {

            //if the item in list1 is lexicographically lesser than the item in list2 then add the list1 item to list3 and increment the pointer of list1.
            if (list1.get(i).compareTo(list2.get(j)) < 0) {
                list3.add(list1.get(i));
                i++;
            }

            //if the item in list2 is lexicographically lesser than the item in list1 then add the list2 item to list3 and increment the pointer of list2.
            else if (list2.get(j).compareTo(list1.get(i)) < 0) {
                list3.add(list2.get(j));
                j++;
            }

            //if both the items are lexicographically equal than add item from list1 to list3 and increment both pointers.
            else if (list1.get(i).compareTo(list2.get(j)) == 0) {
                list3.add(list1.get(i));
                i++;
                j++;
            }
        }
            //If there are no more items in list1 then add the remaining items of list2 to list3.
            if ( i == list1.size()){
               while(j != list2.size()){
                   list3.add(list2.get(j));
                   j++;
               }
            }

            //If there are no more items in list2 then add the remaining items of list1 to list3.
            if (j == list2.size()){
                while( i != list1.size()) {
                    list3.add(list1.get(i));
                    i++;
                }
            }

        return list3;
    }
}
