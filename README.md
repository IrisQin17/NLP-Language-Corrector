# Documentation
## Problem Description

This project is to find the suspicious word or phrase in the whole text. There are two main parts, “Crawler” and “Checker”. “Crawler” is a function to read the correct text (at most 1GB) and store the common usage patterns of words and phrases as the template.

“Checker” is to detect the suspicious phrase and sentences and difference value through comparing the difference between suspicious phrases and corresponding similar phrases in template.

Excluded minimum requirements, there are some other possible features we can add into this project. Graphical User Interface Suggest reasonable corrections to suspicious phrases Crawl social media posts Allow human assessment of suspicious samples and non-trivially feed them back into the system. Extend to a language in which none of the team members have fluency

## Group Members

Ting Zhang 

Bowen Qin   (bwqin@bu.edu)

Ganghao Li

Hao Zuo

Yuan Wei	(yuanwei@bu.edu)

## Implementation

### Crawler:

Crawler:

​	In our project,  the input is the path of file of URLs, to implement we use an original URL (which is “http://www.rndsystems.com/cn” in test) to fetch URLs in that URL web, and form a txt containing these URLs and then read each URL in URL-list.txt line-by-line which is stored locally in Resource Director.

For each URL, use crawler fetch the corresponding web content into local file, and name the file with each URL’s index in URL-list.txt.All the inter local stored files will be stored in local Resource directory.There is an interface named “crawler.java”, “URL2File.java” implements this interface and overrides the function of “StoreFile” whose input is a specific string representing URL.

​    In the first step, correct english language content is read in and stored into our data storage which is a directed graphThen in the step of calling checker, checker will read and parse the content of a specific file, find the corresponding words and phrases in graph, and check their neighbors. Finally the checker will give out the score.



### Tokenizer:

For every word in sentences,  it is assigned in accordance with its syntactic functions. We call them part of speech (pos). Even though the same word, it may have different syntactic functions. For example, the word "fall" may act like a verb means move downward and also it could act like a noun with the same meaning as "autunm". That is to say, the we should consider both the POS and the word itself. Hence we should standardize the word with the word and its POS.

The tokenzier should standerlize the input with:

- Part of Speech (POS)
- Word

In our project, we created a class named "tokenType" which contains the elements we mentioned. Words are added with pos. The POS are get from Stanford NLP API using its trained model. 

All of our reasult is store in a List `List<List<tokenType>> result`

For now, we support German, Spanish, French(which none of the team members have fluency), Chinese and English.
### Database:
![image](img/graph.png)


### Checker:



### Graph User Interface:

![image](img/UI.png)

*NOTE: the GUI now is temporaly offline, since we haven't deal with thread issues.

As it show in our graph above, we offer the PATH of the URL files and  PATH of File which we'd like to check.The input path would be pass to the crawler.class  to get the URL and generate the text file.

Three language detect options are offered in our project. For each language we select, a different model would be applied for analysis. 



## Reference
[1] Lionel Clément, Kim Gerdes, Renaud Marlet. A Grammar Correction Algorithm – Deep Parsing and Minimal Corrections for a Grammar Checker. 14th conference on Formal Grammar (FG 2009), Jul 2009, Bordeaux, France. ffinria-00396240f (Available at: https://hal.inria.fr/inria-00396240/document, Accessed: 26 February 2020)
Explanation: This article presents the central algorithm of an open system for grammar checking, based on deep parsing, which is helpful to our project.

[2] Manning, Christopher D., Mihai Surdeanu, John Bauer, Jenny Finkel, Steven J. Bethard, and David McClosky. 2014. The Stanford CoreNLP Natural Language Processing Toolkit In Proceedings of the 52nd Annual Meeting of the Association for Computational Linguistics: System Demonstrations, pp. 55-60.[ https://nlp.stanford.edu/pubs/StanfordCoreNlp2014.pdf ] (Available at: https://stanfordnlp.github.io/CoreNLP/, Accessed: 19 March 2020)

Explanation: Stanford CoreNLP API -- We use Stanford CoreNLP API to analyze each word's part of speech.

[3] Junghoo Cho, CRAWLING THE WEB: DISCOVERY AND MAINTENANCE OF LARGE-SCALE WEB DATA, Nov 2001, (Available at: http://oak.cs.ucla.edu/~cho/papers/cho-thesis.pdf, Accessed: 26 February 2020)
Explanation: Web crawling to crawl the sentence text.

[4] John Lee, Stephanie Seneff, Automatic Grammar Correction for Second-Language Learners, 2006 (Available at: https://www.isca-speech.org/archive/archive_papers/interspeech_2006/i06_1299.pdf, Accessed: 19 March 2020)

Explanation: A sentence level, generation-based approach to grammar correction algorithm.

[5] Grigori Sidorov. Syntactic Dependency Based N-grams in Rule Based Automatic English as Second Language Grammar Correction. International Journal of Computational Linguistics and Applications, vol. 4, no. 2, pp. 169-188, 2013. (Available at: http://www.cic.ipn.mx/~sidorov/IJCLA_SN_GRAMS_2013.pdf, Accessed: 26 February 2020)
Explanation: A system for automatic English grammatical error correction.


## Code
- All complete, working Java 8 code used in your implementation.
- All data needed by your project to run (or a simple, publicly accessible link thereto).
- All testing code utilized to observe the correctness of your code.

## Work breakdown
#### Ting Zhang:


#### Bowen Qin:
- Worked on Tokenizer: Learning Stanford CoreNLP API and deployed its environment using Maven. Successfully developed tokenizer that support German, Spanish, French(which none of the team members have fluency), Chinese and English.

- GUI design and improvement: Using Swing UI Designer.
#### Ganghao Li:

#### Hao Zuo:

#### Yuan Wei: