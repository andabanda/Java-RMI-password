[![Work in Repl.it](https://classroom.github.com/assets/work-in-replit-14baed9a392b3a25080506f3b7b6d57f295ec2978f6f33ec97e36a161684cbe9.svg)](https://classroom.github.com/online_ide?assignment_repo_id=3493814&assignment_repo_type=AssignmentRepo)

# Overview

My distributed systems assignment is a RMI based server application that has three functionalities: 
* Generate a password
* Encrypt a given password. 
* Test password Strength

The idea behind my assignment was to solve a reoccurring problem that often happens when internet users attempt to register on websites. Often times when you quickly register for a website for something that you are going to use maybe once or twice, it is dangerous to reuse a password that you use for more private and confidential accounts. Therefore, the best solution is to make a new password on the spot. However, nowadays with increasing privacy concerns, it can be difficult to think of a password on the spot that fulfills all the requirements of a website. Therefore, my application can solve this problem and generate a password on the spot as well as encrypt any other password you want to use. 

Now I belive my assignment does a better job at making the generated password accessible and usable. Additionally, I believe that it does a great job in allowing users to understand how encryption works with the use of hash codes and salts.  

## How to run the application

1) When you start up the application, you are met with a simple interface asking you to select a specific functionality of the application: either password generator, password encryption, or password strength checker.

 

2) If you select password encryption, you will be met with the first novel feature, which prompts the user to select a difficulty (difficulty choice being the first novel feature). Once a difficulty has been selected, it will prompt the user to select a password length and will then display the generated password and print to file: 
“list.txt” and copy the password to clipboard. The program will then terminate.

3) The second functional feature is accessed by selecting it as an option when you first run the program. The program will prompt the user to select an encryption type, MD5, SHA-512, or PBKDF2. You can then follow the instructions on screen until termination. 

4) The last function is the password checking function. This will check the password and inform the users of their password strength.
 
 

