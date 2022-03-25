# 5Letter-SpringBoot-App
 Spring Boot Version from scratch

Start date: OCTOBER 2021

### Progress 3/25/22:
After practicing a lot with the Twitter project. I decided to jump on this again. It took me a while to understand how to use DTO vs Sessions. But after playing around and looking at other code, I started to understand.

I then created a new list on things I need to work on as of today:

## Implementations and features
- [ ] add level choice
- [ ] add name field for scoring purposes
- [ ] add score system
- [ ] implement hibernate JSP for processing and saving a score into a database
- [ ] add highscores to view (top 5)

## Things to fix:
- [ ] (D) fix the service tests
- [ ] (B) FIX how to get the error to display on Thymeleaf? Code seems right...
  - [ ]  find a way to make the length error only show when there is something typed. And empty only when empty.
- [ ] (C) FIX why is it loading the guess x4 every time it loads index? Then error if null always shows.
    - Update 22/2/22 error is showing when @Valid is added to first method, and not loading NULL. This is desired, so now I need to figure out how to NOT load it.
- [ ] (D) at the end: clean up all sessions requests into variables for readability
- [ ] (D) move all text to a property file for easy update and implementation
