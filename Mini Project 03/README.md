# Using GitHub in Eclipse with EGit and the GitHub Mylyn Connector
This guide assumes:
- You already have a GitHub account (but don't necessarily know how to use it)
- You have an understanding of how Eclipse works

By the end of this guide, you should have an understanding of how to do most of the basic operations related to GitHub and its Issues from within Eclipse.

## Create a GitHub Repository
The first major step on your journey to running a GitHub project is to create a repository to store your project in.
[GitHub has an excellent guide on the subject here.](https://help.github.com/articles/create-a-repo/)
Click the **+** button in the upper right corner of a GitHub page while logged in, click **New Repository**,
name it, describe it, and decide whether you want to operate in public (for free) or privately (at monetary cost).

Since you are most likely using a Java project, we recommend that you add a Java `.gitignore` file
to the repository when it's created. This will prevent you from adding compiled `.class` files and
crash logs to your repository. If you forgot this step, you can copy the contents of
[the GitHub "official" Java `.gitignore`](https://github.com/github/gitignore/blob/master/Java.gitignore)
to a new `.gitignore` file in your repository and commit it later. If you already added some of those sort
of files, don't forget to remove them from your repository in the next commit!

## Create and Push an Eclipse Project

## Commit and Push changes

## Clone an Existing Repository

## Hook up GitHub Issues to Mylyn (the Eclipse task list)

## Add an Issue

# Mini Project 3 Assignment
For this mini project, your team is assigned a research task
where you have to **find a free bug tracking/issue tracking tool
that integrates with Eclipse** and allows collaborative team work
(e.g. Git plugin on Eclipse, where everyone on the team can see everything).

1. You will have to write a short report **explaining** the tool **features**,
   where to **download** it, how to **install** it,
   and **a user manual** on how to use it.
2. You will also need to **make a tutorial video**
   that shows "dummies" how to make it work!

## What's to Come
This README will end up being replaced with said user manual (_probably_
written in Markdown) and include a link to the video. Keep an eye out!

# A Start / References
## Features
- [GitHub Features (everything)](https://github.com/features)
- [GitHub Issues Guide](https://guides.github.com/features/issues/)
  - See also everything else on https://guides.github.com/

## Download/Installation
- Git + GitHub Eclipse Integrations: [Via GitHub](https://eclipse.github.io/) ->
  [Overview Site](http://eclipse.org/egit/download/)
  - [Eclipse Git Support Install Site](http://download.eclipse.org/egit/updates)
  - [GitHub Issues Integration Install Site](http://download.eclipse.org/egit/github/updates)

Installing the Eclipse stuff is really straightforward;
just pop the install sites above into the `Help > Install New Software...` menu.
Click next/finish a few times and let Eclipse do the rest.

Actually using the plugins is where the writing effort needs to go.

## Partial Issues "Guide"
1. Show Task List view in Eclipse
2. Add a new task respository of the GitHub Issues type
	- Server -> your repository
	- Label -> Doesn't matter, pick one
	- User id -> GitHub username (needed for issue creation)
	- Password -> GitHub password (also needed for creation); tick Save Password
3. Add a query that doesn't filter anything
4. Issues now present. Use the Task List's New Task button to make new issues.
5. Make sure to use the synchronize button to keep the list up to date with what GitHub sees
