
package laboratorio_variante;

/**
 * This class is used to save the preloaded data of the students
 * from a txt and implements the compareTo function defined in the Groups class
 * to be used to sort elements
 * @author Adrian Chavarria, Joselyn Abarca, Carlos Barroso, Jeison Alvarez
 */
public class Student implements Comparable<Student> {
    public String name;
    public String surname;
    public int grade;
    /** 
     * The constructor is defined which receives two variables of type String
     * and a variable of type int
     * @param name name of the students
     * @param surname student's card
     * @param grade student's grade
     */
    public Student(String name, String surname, int grade) {
        this.name = name;
        this.surname = surname;
        this.grade = grade;
    }//end constructor
    /** 
     * 
     * @param other parameter that you receive to compare from the groups class
     * @return returns the comparison performed and sorted
     */
    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.grade, other.grade);
    }

    // Getters and setters (omitted for brevity)
}
