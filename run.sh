#!/bin/bash

for i in `seq 1 10`;
do
	grails clean
	grails test-app -integration -Dgrails.env=test
done	
