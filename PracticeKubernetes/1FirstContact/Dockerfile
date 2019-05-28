# Build a ruby on rails image
FROM ruby:2.3.1
RUN mkdir /lobsters
WORKDIR /lobsters
ADD Gemfile /lobsters/Gemfile
ADD Gemfile.lock /lobsters/Gemfile.lock
RUN apt-get update && ap-get install nodejs -y
RUN bundle install
ADD . /lobsters
ENTRYPOINT ["/lobsters/bin/rails", "server"]

