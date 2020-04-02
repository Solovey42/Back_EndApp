#!/bin/bash

tests_passed=0
tests_failed=0

function test() {

    test_name=$1
    params=$2
    expected_status=$3
    # shellcheck disable=SC2034
    output=$(./RUN.sh "$params")
    actual_status=$?

    if [ $actual_status -eq "$expected_status" ]
    then
    ((tests_passed++))
    echo -e "\033[32m$test_name passed \033[0m"
    else
    ((tests_failed++))
    echo -e "\033[31m$test_name failed \033[0m"
    fi

    echo -e "app.jar $params \nexpected: $expected_status \nactual: $actual_status \n"
}

test "T1.1" "" 1
test "T1.2" "-h" 1
test "T1.3" "-q"  0
test "T2.1" "-login vasya -pass 123"  0
test "T2.2" "-pass 123 -login vasya" 0
test "T2.3" "-login VASYA -pass 123"  2
test "T2.4" "-login asd -pass 123"  3
test "T2.5" "-login admin -pass 1234" 4
test "T2.6" "-login admin -pass admin	"  0
test "T3.1" "-login vasya -pass 123 -role READ -res A"  0
test "T3.2" "-login vasya -pass 123 -role DELETE -res A"  5
test "T3.3" "-login vasya -pass 123 -role WRITE -res A"  6
test "T3.4" "-login vasya -pass 123 -role READ -res A.B"  0
test "T3.5" "-login admin -pass admin -role WRITE -res A.B.C"  0
test "T3.6" "-login vasya -pass 1234 -role DELETE -res A"  4
test "T3.7" "-login vasya -pass 123 -role WRITE -res A.B.C"  0
test "T3.8" "-login admin -pass admin -role READ"  0
test "T3.9" "-login admin -pass admin -role EXECUTE -res A"  6
test "T3.10" "-login admin -pass admin -role WRITE -res A.A"  6
test "T4.1" "-login vasya -pass 123 -role READ -res A -ds 2020-01-01 -de 2020-02-01 -vol 20"  0
test "T4.2" "-login vasya -pass 123 -role READ -res A -ds 2020-01-01 -de 2020-15-01 -vol 20"  7
test "T4.3" "-login vasya -pass 123 -role READ -res A -ds 2020-01-01 -de asd -vol 20"  7
test "T4.3" "-login vasya -pass 123 -role READ -res A -ds 2020-01-01 -de 2020-02-01 -vol asd"  7
test "T4.5" "-login vasya -pass 123 -role READ -res A -ds 2020-01-01 -de 2020-02-01 -vol -1"  7

echo "Tests passed: $tests_passed"
echo "Tests failed: $tests_failed"

if [ $tests_failed -eq 0 ]
then
exit 0
else
exit 1
fi