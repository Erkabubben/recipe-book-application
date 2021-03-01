# Assignment 4

## Object Orientation and Design Patterns

This assignment makes full use of object orientation by design patterns, predefined solutions to common problems. It also utilises unit testing as part of quality assurance for your code. 

## For students taking 1DT904 (Civilingenjörer)
Kursen 1DT904 avslutas med ett projektarbete som ni ska göra två och två.  Projektet ska även dokumenteras och presenteras i slutet av kursen i stället för en skriftlig tentamen. Ni gör projektet beskrivet här men lägger till delarna för att skapa etiketter (labels) samt fler tester från VG-beskrivningen nedan eftersom ni jobbar två och två.

## For all

> Carefully read the assignment rules as defined on Moodle. In brief, do the assignments yourself, hand them in at the deadline and don't cheat.

> Do not hesitate to ask your teaching assistant at the practical meetings (or the teacher at the lectures) if you have any problems. You can also post a question in the Slack channel for the course.

> **Submission:** you submit your solution using git and GitLab following the instructions given for the course. Remember to do a merge request but _do not_ press **merge**.

## Instructions
This is a larger project where you will begin by creating suitable diagrams for defining your solution before you start to code. The project, which for students taking 1DV502 is assignment 4 and for students taking 1DT904 is the project, consists of mandatory parts and optional, where the optional are for a higher grade (A or B). Notice that you are not guaranteed a higher grade just because you implement the optional parts, you just have a possibility to get it. 

<!---As there are quite a few things to implement, we will be a bit more relaxed than usual. To be able to get a C grade, you need to fulfil all the requirements listed below. However, if you miss some of them, you will be given a lower grade, a D or an E. This means that it is _possible_ to pass the assignment without doing everything even though we hope that you give all parts a try, of course.--> 

Collect all parts of your solution in a manageable way in your repository for us to look at later. It is up to you to find a suitable way, but remember to make it easy for us to correct - for example, do not just place a large number of image files in a folder and call that analysis and design. Add a document explaining your analysis and design, preferably in Markdown format so that it is easily read from GitLab.

## Approaching
Start small! Begin by trying to get an idea of the structure of the application by beginning to draw a class diagram. Try to find out the classes needed to fulfil the requirements, but also have in mind that you might need to revise them later. Try to find what relations the different classes have, what might be dependent on what and so on.

When coding, do it in small, small steps. Do not add too much functionality at once, but rather try to add one or two methods at a time and see to that it works. If it does not add too much overhead for you, try to add tests as early as possible in the process. 

Reading and parsing (as well as saving) the file is something we have not covered extensively in the course, so put some time to try to figure that out. It is always a good idea to try out parts that you are unfamiliar with in a separate project without the overhead of all the other parts of the program. We will allow you to define the structure of the text file to read and save yourselves, but try to make it as flexible as possible, see more information below.

Be prepared to change the class diagram as you go along with the coding. This happens in real life as well, but perhaps more so when we are this early in the educational programme. That said, see the class diagram as a help to structure your thoughts. Add other diagrams _as you see fit_ if they help you.

# The Recipe App
You are to create an application that handles recipes. In essence this is a digital cooking book with recipes that you can add, delete and view via the terminal. All recipes are to be stored on file and read when the program starts (as well as saved when the program is closed). As it is digital, you should be able to do some additional things as compared to a normal book, for instance change the number of portions or list based on ingredient or max price. Below are the requirements for the application.

## Passing Grade Requirements
 1. Create at least a class diagram for the application before you start implementation, save in `intended.md` This should be committed to the repo before the implementation is added. I.e. you should show that you have spent some time designing.
 2. Create and add an ingredient, it should have a name, and a unit of measure (i.e. grams, litre, piece) and a price. The ingredient name is unique. For example; milk:litre:10.5 or, egg:piece:4.
 3. List all available ingredients.
 4. Look at the details of a particular ingredient
 5. Delete a particular ingredients (note that this may effect recipes)
 6. Add a recipe with a name, number of portions, ingredients, ingredient and amounts and comments, and step by step instructions.
    * note that you may need the same ingredient several times with different purposes in a recipe. I.e. Butter 100g in batter, Butter 20g for frying.
 7. List all available recipes
 8. Look at a particular recipe
    * user should be able to define the number of portions needed and the recipe should be updated accordingly. Note that some ingredients cannot be divided, for example eggs, this should be handled by the application rounding to the nearest even divisor above the desired number of portions. The price of the recipe should be calculated.
 9. Delete a particular recipe
 10. Search available recipes based on ingredient name, or the max price
     * the design should use the `strategy pattern`
     * Feel free to add more search types, e.g. recipe name etc.
11. The application should load all ingredients and recipes from disk _when the program i starting_. See File Format
12. The application should save all ingredients and recipes to disk _when the program is ended_. See File Format
13. The user interface should be console based and offer a menu with possible sub menus of your choice.
14. At least one automatic test case for one operation.
15. Create an updated class diagram showing the final design after implementation, save in `design.md`
16. Good quality of code (for example naming, standards, duplication)
17. An object oriented design and implementation. This includes but is not limited to:
    * Objects are connected using associations/dependencies and not with keys/ids.
    * Classes have high cohesion and are not too large or have too much responsibility.
    * Classes have low coupling and are not too connected to other entities.
    * Avoid the use of static variables and operations as well as global variables.
    * Avoid hidden dependencies.
    * Information should be encapsulated.
    * Use a natural design, the domain model should inspire the design.
18. Simple error handling. The application should not crash but it does not need to be user friendly.
19. Add a README.md that explains what is working and what is not, and any usability issues we need to know when trying your application.

 ## Higher Grade Requirements
 1. Edit an ingredient (note that this may effect recipes)
 2. Edit a recipe.
 3. Add labels to ingredients. For example an ingredient could be labelled as `vegan` or `dairy free`, a recipe containing all vegan or dairy free ingredients could itself be marked as vegan/dairy free.
 4. Add labels to recipes. For example a label a recipe as desert.
 5. Search using labels.
 6. Advanced recipe search where several search expressions can be combined e.g. `(tomatoes && ham) || mozzarella`. The design should use the composite pattern.
 7. Grading of recipes (with search on grade etc).
 8. Several automatic test cases for different operations and classes.
 9. Automatic build pipeline using Gradle and GitLab
 10. Automatic quality tests with checkStyle and findBugs use `CodeQualityTests.java` from assignment 3
 11. Create packages according to roles of the classes, that is, do not put everything in one package but separate them based on what they do.

### Ingredient Label Categories
- vegan (vegan ingredient)
- dairy (i.e milk, cheese, etc)
- gluten (contains the protein gluten, i.e. most grains)
- fish (contains fish or seafood)
- meat (contains meat in some form)
- ...


### Recipe Label Categories
- dessert
- dinner
- breakfast
- lunch
- bbq
- vegetarian (auto label based on no meat, fish)
- vegan (auto label all vegan ingredients)
- dairy free (auto label no dairy)

## File Format
It is up to you to define the file format. Try to make it possible to work with using the method _split()_ in **String**. One way to see it is that you put one recipe per line in the file and separate the parts with semicolon. There might be useful to add lists of thing (the ingredients for example) and try to separate them in another way. Below could be the beginning of an example:
```
Gingerbread biscuit; 100, pieces; [1, litre, flour; 2, dl, cream; 1, spoon, cinnamon]; *Mix everything, *Wait one day, *Make figures
```
The above is by no means covering everything and should not be seen as "the solution", but the beginning of it. Also notice that you might need to use two files, one for the recipes and one for ingredients.

Note requirements regarding loading and saving, it is easier to load everything at startup and save everything at program end.

You are free to use any format you like, if you feel adventurous you may use something like JSON or XML (and add suitable libraries), but you then need to provide easy to follow build instructions. Or, preferably use gradle.
