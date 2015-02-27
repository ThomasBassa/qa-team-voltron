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

## Clone your Repository into an Eclipse Project
With your repository up and running, you can then clone it to your Eclipse workspace to make changes.
In Eclipse, use the menus to select `File > Import...`. This can also be found by right-clicking in
an empty part of the Package Explorer. In the Import wizard, select `Git > Projects from Git`.
**Do NOT select `Repositories from GitHub`, as this will _not_ create an Eclipse project for you.**

On the next screen following Projects from Git, select `GitHub`, then click Next.
Enter the name of your repository (the part after your name and slash-- for example,
this repository is named `qa-team-voltron`) into the first text field and click Search--
hopefully you made your repository name unique! Select your repository, then click `Next`.

The next screen will list the branches of your repository-- if this is a brand new repository,
the only branch will be `master`. You don't need to worry about this; click `Next`.

The next dialog will ask you where the _files_ will be copied to. By default, this will be in a
subfolder of a `git` folder within your home directory. We recommend changing this directory to a new folder
within your Eclipse workspace, but you may select any place you can find later.
The remaining settings should be left as default (initial branch `master`,
`Clone submodules` unchecked, remote name `origin`).

The next step asks you what kind of import should be used: an existing Eclipse project, the New Project wizard,
or as a general project.
- If the repository is empty, select `Import using the New Project wizard`.
- If the repository has already had an Eclipse project pushed to it (e.g. there are `.classpath` and `.project`
  files present) then select `Import existing Eclipse projects`.
- Do not import as a general project, as this will make it annoying to configure Eclipse to actually compile your project.
 
For a new project, select `Java > Java Project` as though you were starting from the Eclipse `New...` menu.
From this point, the process is the same as making an ordinary Eclipse project, except for one key difference:
**the name AND location of the new project MUST be the same as the folder you selected for the repository.**
If you select this correctly, the wizard will grey out the options for the JRE and Project layout.
Eclipse will create a project using the default settings. Click `Finish`.

At this point, you will have an Eclipse project that is all ready to go for making changes.

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
