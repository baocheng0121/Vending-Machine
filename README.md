# Vending Machine System
> This is a simple vending machine software application programmed in Java.
There are four user types: customer,seller, cashier and owner.

One major functionality is accessible for all types of users, which is Login(choose as a customer or a staff).

Customers can buy items in the vending machine by card or cash.

Seller can modify the item details (add profuct,refill product,modify price,display items and generate report)

Cashier can modify cash quantity,display avaliable cash report and transaction report.

Owner can manage everything about the vending machine(users,items and cash).


## Setup
How to **INSTALL**

`Download the package from https://github.sydney.edu.au/SOFT2412-2020S2/T18A_Group1_Asm2.git`

[You are expected to run the project using Gradle]

**SET UP** configuration of dependencies or other libraries using Gradle by input the code in terminal:

`gradle build`

## Usage
**RUN** the project by input the following command in terminal:

`gradle run`

Then, you will enter the **log in** interface, you can obatain some valid usernames and passowrds we provide you in file `vendingmachine/Log/StaffInformation.txt`  or  `vendingmachine/Log/UserInformation.txt`


[If you choose **Anonymous**]
 you can buy items without user name or password.


[If you are logging in as **Customer**]

you will enter the ChooseItem Interface by default, where you can choose the item you want to the cart with the quantity.

When you want to check out, you can will go to the Checkout interface(auto close in 2 min) to choose whether pay by card or cash.



[If you are logging in as **Seller**]

you will be able to access the Seller Interface, where you can modify the item details (add profuct,refill product,modify price,display items and generate report).

You can find the item report in 'vendingmachine/Snacks/Database/ItemReport.txt'.


[If you are logging in as **Cashier**]

you are able to enter the Cashier interface,where you have the ability to  modify cash quantity,display avaliable cash report and transaction report.



[If you logging in as a **Owner**]

you will see the Owner interface

You can do a lot of things! (eg. modify the users,items or cash in the vending machine).


 

## Test
**Test** the project by input the following command in terminal:

`gradle test`

Alternatively, a jacoco test report can be generated from which the test coverage is visualised if input the following command:

 `gradle test jacocoTestReport`
## Contribution/Collabration
You are more than welcomed to contribute or collabrate on the codebase by following the instructions below:

a) Found some bugs unfixed in the code or having troubles running the projects?

`Please feel free to go to the ISSUE page and hit "New Issue" telling us about your concern, and we will try to fix it as soon as possible`

b) Detecting some inappropriate design as a user?

`You are more than welcomed to hit the "New Issue", and do not forget to assign it the "user story" label, which will be extremely helpful in our accumulation of use cases.`

c) Feeling like to fix the bugs yourselves or collabrate with us
 
 `We do encourage you to download the files into your local enviroment and enjoying the process of problem solving. However, please do not directly pushing your workflow into your branches. If you are willing to share your code, please contact our colleagues first.`
