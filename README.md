# Documentation
## Problem Description

This project is to find the suspicious word or phrase in the whole text. There are two main parts, “Crawler” and “Checker”. “Crawler” is a function to read the correct text (at most 1GB) and store the common usage patterns of words and phrases as the template.

“Checker” is to detect the suspicious phrase and sentences and difference value through comparing the difference between suspicious phrases and corresponding similar phrases in template.

Excluded minimum requirements, there are some other possible features we can add into this project. Graphical User Interface Suggest reasonable corrections to suspicious phrases Crawl social media posts Allow human assessment of suspicious samples and non-trivially feed them back into the system. Extend to a language in which none of the team members have fluency

## Group Members

Ting Zhang

Bowen Qin

Ganghao Li

Hao Zuo

Yuan Wei	(yuanwei@bu.edu)

## Implementation

### Crawler:





### Tokenizer:

For every word in sentences,  it is assigned in accordance with its syntactic functions. We call them part of speech (pos). Even though the same word, it may have different syntactic functions. For example, the word "fall" may act like a verb means move downward and also it could act like a noun with the same meaning as "autunm". That is to say, the we should consider both the POS and the word itself. hence we should standardize the word with the word and its POS.

hence the tokenzier should standerlize the input with:

- Part of Speech, POS
- Word





### Database:
### Checker:



## Reference



## Work Breakdown

​    

- group members (together with any information you feel comfortable including: pictures, e-mails, etc.)

- a high-level description of the implementation, with particular emphasis on the design decisions related to data structures and algorithms

- a list of the features (from the project proposal) that have been implemented:
    - for each feature implemented, provide a description of how it was implemented with an emphasis on data structures and algorithms used.
    - if you have changed (added or removed) a feature from your MidtermStatusReport, please also provide an explanation of the change.
    
- relevant references and background materials.
## Code
- All complete, working Java 8 code used in your implementation.
- All data needed by your project to run (or a simple, publicly accessible link thereto).
- All testing code utilized to observe the correctness of your code.
## Work breakdown
- a detailed statement of what each member of the group contributed to the project, signed by all members of the group.
    - You may keep this private instead, if you wish, by e-mailing it directly to the instructor (trachten@bu.edu), cc'ed to the entire group.
    - You may also link to your Jira project for material that you wish to remain private and accessible only to the course staff.

