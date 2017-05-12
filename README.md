[![Build Status](https://travis-ci.org/ejahns/selenium-stateful.svg?branch=master)](https://travis-ci.org/ejahns/selenium-stateful)
# selenium-stateful


## Problem Statement
Many testers using Selenium employ a Page Object Model (POM), in which test objects (pages) are
entirely separate from the test scripts. In this model, a web page to be tested is represented by
a class that acts as a container for selectors and has methods for accessing fields on the page.

These `Page` objects have a strange combination of stateful and stateless characteristics. They are
stateless in that it would not make sense to store some aspect of the page state in a class field
because a change in the page state would not be reflected. However, the web page the objects represent
certainly have state, which the user can access through invoking methods contained in the page object.

An inefficiency arises when attempting to remember the state of a web page across a test. For example,
the user may wish to write a test that navigates to a page, sets up some configuration (e.g. check some boxes,
enter some text into fields, make some selections in a dropdown), saves the configuration, and then navigates
back to the page to ensure that the configuration has been preserved.

One would like to compare the `Page` object created before and after the save, but the `Page` doesn't actually
contain the state of the webpage, but only acts as a handle to access components of the state. This
requires creating boiler-plate `State` classes that merely act to capture the state of the page at some
instant in time.