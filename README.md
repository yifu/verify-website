# verify-links

(Verify-links is just my first Leiningen generated projects.) Its
purpose is to browse a website for its links and check they are still
responsiveful. Broken links are reported. Here is an output example:

```
Check the website  http://yves-ba.com
Fetching url:  http://yves-ba.com  ...  Ok.
Fetching url:  http://yves-ba.com/resune.html  ... BROKEN LINK!!
Fetching url:  http://yves-ba.com/blog.css  ...  Ok.
Fetching url:  http://htmlandcssbook.com/  ...  Ok.
...
```

Basicaly, it works with a bread-first-search algorithm (well I've
tried but I ma still wondering how to implement a queue in Clojure)
without going out the domain name provided on the command line. In the
last example 'http://htmlandcssbook.com' is checked out, but its
content is not parsed in order and thus its own links are not
followed.

## Installation

Pull the project and run:

    $ lein uberjar


## Usage

Just push the root of the website you want to check as in:

    $ java -jar verify-links-0.1.0-standalone.jar http://yves-ba.com

## Options

No options for the moment.

Considering the verbose one, and reporting only broken links by default.

## Examples

...

### Bugs

Surely there are a lot of bugs. I didn't perform that much tests, it
is just a toy project.

## License

Copyright © 2013 Yves Baumes

Distributed under the Eclipse Public License, the same as Clojure.
