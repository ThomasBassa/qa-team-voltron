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

## Sketchy Use Flow
1. Make GitHub repo
2. Make Eclipse project
3. Make _local_ Git repo in same spot as project
4. Commit to local repo, then push to remote (GitHub)
5. ???
6. Profit

May want to clone the GitHub repo instead. Not sure whether pushing to remote
from a local-only repo is more or less difficult than cloning first.

Regarding issues...

1. Show Task List view in Eclipse
2. Add a new task respository of the GitHub Issues type
	- Server -> your repository
	- Label -> Doesn't matter, pick one
	- User id -> GitHub username (needed for issue creation)
	- Password -> GitHub password (also needed for creation); tick Save Password
3. Add a query that doesn't filter anything
4. Issues now present. Use the Task List's New Task button to make new issues.
5. Make sure to use the synchronize button to keep the list up to date with what GitHub sees
