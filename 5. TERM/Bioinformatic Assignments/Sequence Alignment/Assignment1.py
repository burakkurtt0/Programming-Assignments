"""


Burak Kurt

2200765010




"""



import blosum as bl
import sys



def Read_Input(file):
    file = open(file,"r")
    seq1 = file.readline().strip()
   
    seq2 = file.readline().strip()
    scoring_matrix = file.readline()
   
    matrix_Ver = int(scoring_matrix[6:])

    score_matrix = bl.BLOSUM(matrix_Ver)

    gap_open = int(file.readline())
    gap_extend = int(file.readline())
    
    parameters = (gap_open,gap_extend)

    file.close()

    return seq1,seq2,score_matrix,parameters




def globalAlignment(seq1,seq2,score_matrix,parameters):

    gap_open,gap_extend = parameters

    

    matrix = [[0 for j in range(len(seq2) + 1)] for i in range(len(seq1) + 1)]

    traceback = [["" for j in range(len(seq2) + 1)] for i in range(len(seq1) + 1)]

    for i in range(1, len(seq1) + 1):
        matrix[i][0] = gap_open + gap_extend * (i - 1)
        traceback[i][0] = "U" # Up
    for j in range(1, len(seq2) + 1):
        matrix[0][j] = gap_open + gap_extend * (j - 1)
        traceback[0][j] = "L" # Left

   
    for i in range(1, len(seq1) + 1):
        for j in range(1, len(seq2) + 1):

            w_1 = seq1[i - 1]
            w_2 = seq2[j-1]
            match = matrix[i - 1][j - 1] + score_matrix[w_1][w_2]

            # Get the score for a gap in seq1
            delete = matrix[i - 1][j] + (gap_open if traceback[i - 1][j] != "U" else gap_extend)
            # Get the score for a gap in seq2
            insert = matrix[i][j - 1] + (gap_open if traceback[i][j - 1] != "L" else gap_extend)
            # Choose the maximum score
            matrix[i][j] = max(match, delete, insert)
            # Record the traceback direction
            if matrix[i][j] == match:
                traceback[i][j] = "D" # Diagonal
            elif matrix[i][j] == delete:
                traceback[i][j] = "U" # Up
            elif matrix[i][j] == insert:
                traceback[i][j] = "L" # Left


    for row in matrix:
        print(row)


    score = 0
    i = len(seq1)
    j = len(seq2)
    align1 = ""
    align2 = ""
    while i > 0 or j > 0:
        
        if traceback[i][j] == "D": # Diagonal
            align1 = seq1[i - 1] + align1
            align2 = seq2[j - 1] + align2
            i -= 1
            j -= 1
        elif traceback[i][j] == "U": # Up
            align1 = seq1[i - 1] + align1
            align2 = "-" + align2
            i -= 1
        elif traceback[i][j] == "L": # Left
            align1 = "-" + align1
            align2 = seq2[j - 1] + align2
            j -= 1



    score = 0
    line_string = ""
    a1_Gap = 0
    a2_Gap = 0


    for i in range(len(align1)):

        if align1[i] == "-" and a1_Gap == 1:
            score+= gap_extend
            
        if align2[i] == "-" and a2_Gap == 1:
            score+= gap_extend


        if align1[i] == "-" and a1_Gap == 0:
            score+= gap_open
            a1_Gap = 1 

        if align2[i] == "-" and a2_Gap == 0:
            score+= gap_open
            a2_Gap = 1 


        if(align1[i] == "-" or align2[i] == "-"):
            line_string+=" "
        else:          
            a1_Gap = 0
            a2_Gap = 0
            score += score_matrix[align1[i]][align2[i]]
            if(align1[i] == align2[i]):
                line_string+="|"
            else:
                line_string+=" "

    
    print()
    print(align1)
    print(line_string)
    print(align2)
    print()
    print(f"Alignment score: {score}")
    print(f"Identity value: {line_string.count('|')}/{len(align1)}  ({((line_string.count('|')/len(align1)) * 100):.2f}%)")


seq1,seq2,score_matrix,parameters = Read_Input(sys.argv[1])


globalAlignment(seq1,seq2,score_matrix,parameters)