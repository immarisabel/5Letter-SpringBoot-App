# 5Letter-SpringBoot-App
 Spring Boot Version from scratch

## About 5Letter Word Game WebApp
Guess a word of 5 characters. There are only 100 words.

It began as my first project ever in Java. From just console, to now Spring Boot.

This is my 'canvas' app which I use to apply any new knowledge acquired trough courses.

The latest concepts involve Hibernate & Spring Boot as of April 2022.

Later implementations will involve Spring Security.


Start date: OCTOBER 2021

#### Progress 4/4/22:
Finally, implemented Hibernate plus cleanup the view. The next step is organizing the High Score table and implementing Spring Security when I am done with the course.

![screenshoot](https://github.com/immarisabel/5Letter-SpringBoot-App/blob/main/img.png)

![screenshoot](https://github.com/immarisabel/5Letter-SpringBoot-App/blob/main/img_1.png)

#### Progress 3/25/22:
After practicing a lot with the Twitter project. I decided to jump on this again. It took me a while to understand how to use DTO vs Sessions. But after playing around and looking at other code, I started to understand.

I then created a new list on things I need to work on as of today:

## Implementations and features
- [x] add level choice (27/3/22)
- [x] add name field for scoring purposes (27/3/22)
- [x] add score system (27/3/22)
- [x] implement hibernate JSP for processing and saving a score into a database
- [x] add highscores to view (top 5) - need to limit, order and sort in level. But for now this will do.
- [ ] fix validation
- [ ] move game to own page and start page only for name and level

## Things to fix:
- [x] (D) fix the service tests
- [x] (B) FIX how to get the error to display on Thymeleaf? Code seems right... (28/3/22)
  - [x]  find a way to make the length error only show when there is something typed. And empty only when empty.
- [ ] (C) move all text to a property file for easy update and implementation
