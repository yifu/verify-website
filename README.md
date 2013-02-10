# verify-links

Its purpose is to browse a website for its links and check they are
still alive. In other words it looks for broken links. Here is an
output example:

```
$ java -jar verify-links-0.1.0-standalone.jar http://yves-ba.com
Check the website  http://yves-ba.com
Fetching url:  http://yves-ba.com  ...  Ok.
Fetching url:  http://yves-ba.com/resune.html  ... BROKEN LINK!!
Fetching url:  http://yves-ba.com/blog.css  ...  Ok.
Fetching url:  http://htmlandcssbook.com/  ...  Ok.
...
```

Read the logs carefully, above. There is a dead link (trust me) and
its filename is resune.html. The link is mispelled and must have been
written as "resume.html".

### Implementation details

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

* This tools does **not** look for urls in ```"src="``` attributes.
  (only for ```href=```)
* Also it does not parse the html grammar and does not discern *href*
attributes from plain text in the <pre><code> tags.
* The html is supposed to be well formed..

I didn't perform that much tests, it is just a toy project.

### TODO

* Wrap the java call around with a shell script.

## License

Copyright © 2013 Yves Baumes
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are
met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above
      copyright notice, this list of conditions and the following
      disclaimer in the documentation and/or other materials provided
      with the distribution.
    * Neither the name of Yves Baumes nor the names of its
      contributors may be used to endorse or promote products derived
      from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL YVES BAUMES BE
LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN
IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
