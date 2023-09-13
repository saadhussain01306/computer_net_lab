#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Structure to represent a student
struct Student {
    int SID;
    char NAME[50];
    char BRANCH[50];
    int SEMESTER;
    char ADDRESS[100];
};

// Function to insert a new student
void insertStudent() {
    FILE *outfile;
    struct Student student;

    outfile = fopen("students.txt", "a");
    if (outfile == NULL) {
        printf("Error opening file.\n");
        return;
    }

    printf("\nEnter SID: ");
    scanf("%d", &student.SID);

    // Clear the input buffer
    while (getchar() != '\n');

    printf("Enter Name: ");
    fgets(student.NAME, sizeof(student.NAME), stdin);
    student.NAME[strlen(student.NAME) - 1] = '\0'; // Remove the newline character

    printf("Enter Branch: ");
    fgets(student.BRANCH, sizeof(student.BRANCH), stdin);
    student.BRANCH[strlen(student.BRANCH) - 1] = '\0'; // Remove the newline character

    printf("Enter Semester: ");
    scanf("%d", &student.SEMESTER);

    // Clear the input buffer
    while (getchar() != '\n');

    printf("Enter Address: ");
    fgets(student.ADDRESS, sizeof(student.ADDRESS), stdin);
    student.ADDRESS[strlen(student.ADDRESS) - 1] = '\0'; // Remove the newline character

    fprintf(outfile, "%d %s %s %d %s\n", student.SID, student.NAME, student.BRANCH, student.SEMESTER, student.ADDRESS);

    fclose(outfile);
}


// Function to modify the address of a student based on SID
void modifyAddress(int sid) {
    FILE *infile, *tempFile;
    struct Student student;

    infile = fopen("students.txt", "r");
    if (infile == NULL) {
        printf("Error opening file.\n");
        return;
    }

    tempFile = fopen("temp.txt", "w");
    if (tempFile == NULL) {
        printf("Error opening file.\n");
        fclose(infile);
        return;
    }

    while (fscanf(infile, "%d %s %s %d %s", &student.SID, student.NAME, student.BRANCH, &student.SEMESTER, student.ADDRESS) != EOF) {
        if (student.SID == sid) {
            printf("Enter new Address: ");
            scanf("%s", student.ADDRESS);
        }
        fprintf(tempFile, "%d %s %s %d %s\n", student.SID, student.NAME, student.BRANCH, student.SEMESTER, student.ADDRESS);
    }

    fclose(infile);
    fclose(tempFile);

    remove("students.txt");
    rename("temp.txt", "students.txt");
}

// Function to delete a student based on SID
void deleteStudent(int sid) {
    FILE *infile, *tempFile;
    struct Student student;

    infile = fopen("students.txt", "r");
    if (infile == NULL) {
        printf("Error opening file.\n");
        return;
    }

    tempFile = fopen("temp.txt", "w");
    if (tempFile == NULL) {
        printf("Error opening file.\n");
        fclose(infile);
        return;
    }

    while (fscanf(infile, "%d %s %s %d %s", &student.SID, student.NAME, student.BRANCH, &student.SEMESTER, student.ADDRESS) != EOF) {
        if (student.SID != sid) {
            fprintf(tempFile, "%d %s %s %d %s\n", student.SID, student.NAME, student.BRANCH, student.SEMESTER, student.ADDRESS);
        }
    }

    fclose(infile);
    fclose(tempFile);

    remove("students.txt");
    rename("temp.txt", "students.txt");
}

// Function to list all students
void listAllStudents() {
    FILE *infile;
    struct Student student;

    infile = fopen("students.txt", "r");
    if (infile == NULL) {
        printf("Error opening file.\n");
        return;
    }

    while (fscanf(infile, "%d %s %s %d %s", &student.SID, student.NAME, student.BRANCH, &student.SEMESTER, student.ADDRESS) != EOF) {
        printf("SID: %d, Name: %s, Branch: %s, Semester: %d, Address: %s\n", student.SID, student.NAME, student.BRANCH, student.SEMESTER, student.ADDRESS);
    }

    fclose(infile);
}

// Function to list all students of a specific branch
void listStudentsByBranch(char *branch) {
    FILE *infile;
    struct Student student;

    infile = fopen("students.txt", "r");
    if (infile == NULL) {
        printf("Error opening file.\n");
        return;
    }

    while (fscanf(infile, "%d %s %s %d %s", &student.SID, student.NAME, student.BRANCH, &student.SEMESTER, student.ADDRESS) != EOF) {
        if (strcmp(student.BRANCH, branch) == 0) {
            printf("SID: %d, Name: %s, Branch: %s, Semester: %d, Address: %s\n", student.SID, student.NAME, student.BRANCH, student.SEMESTER, student.ADDRESS);
        }
    }

    fclose(infile);
}

// Function to list all students of a specific branch residing in Kuvempunagar
void listStudentsByBranchAndAddress(char *branch, char *address) {
    FILE *infile;
    struct Student student;

    infile = fopen("students.txt", "r");
    if (infile == NULL) {
        printf("Error opening file.\n");
        return;
    }

    while (fscanf(infile, "%d %s %s %d %s", &student.SID, student.NAME, student.BRANCH, &student.SEMESTER, student.ADDRESS) != EOF) {
        if (strcmp(student.BRANCH, branch) == 0 && strcmp(student.ADDRESS, address) == 0) {
            printf("SID: %d, Name: %s, Branch: %s, Semester: %d, Address: %s\n", student.SID, student.NAME, student.BRANCH, student.SEMESTER, student.ADDRESS);
        }
    }

    fclose(infile);
}

int main() {
    int choice, sid;
    char branch[50], address[100];

    while (1) {
        printf("\nMenu:\n");
        printf("1. Insert a new student\n");
        printf("2. Modify student's address\n");
        printf("3. Delete a student\n");
        printf("4. List all students\n");
        printf("5. List all students of a specific branch\n");
        printf("6. List all students of a specific branch residing in Kuvempunagar\n");
        printf("7. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                insertStudent();
                break;
            case 2:
                printf("Enter SID to modify address: ");
                scanf("%d", &sid);
                modifyAddress(sid);
                break;
            case 3:
                printf("Enter SID to delete: ");
                scanf("%d", &sid);
                deleteStudent(sid);
                break;
            case 4:
                listAllStudents();
                break;
            case 5:
                printf("Enter branch to list students: ");
                scanf("%s", branch);
                listStudentsByBranch(branch);
                break;
            case 6:
                printf("Enter branch: ");
                scanf("%s", branch);
                printf("Enter address: ");
                scanf("%s", address);
                listStudentsByBranchAndAddress(branch, address);
                break;
            case 7:
                return 0;
            default:
                printf("Invalid choice. Try again.\n");
        }
    }

    return 0;
}
