package org.example.springdatajpatest.bestPractice.kakaoPayTech;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * https://tech.kakaopay.com/post/implementing-tdd-in-practical-applications/
 */
public class EachTestRunnerTest {

    // Red Test 1
    // EachTestRunner 클래스가 없어서 컴파일 에러 발생
    @Test
    void 전달받은_클래스의_메서드를_실행한다() {
        // arrange
        RefactoringEachTestRunner runner = new RefactoringEachTestRunner(Dummy.class, "run");

        // act
        runner.run();

        //assert
        assertThat(runner.wasRun()).isTrue();
    }

    static class Dummy {
        public void run() {}
    }

    // Red Test 2
    // wasRun 메서드가 false 를 리턴해서 테스트 실패
    static class RedEachTestRunner {
        public RedEachTestRunner(Class<?> clazz, String methodName) {

        }

        public void run() {

        }

        /**
         * Expecting value to be true but was false
         * Expected :true
         * Actual   :false
         * */
        public boolean wasRun() {
            return false;
        }
    }

    // Green Test 1
    // wasRun 메서드 리턴 값을 true 로 수정해서 테스트 성공
    static class GreenEachTestRunner {
        public GreenEachTestRunner(Class<?> clazz, String methodName) {

        }

        public void run() {

        }

        public boolean wasRun() {
            return true;
        }
    }

    // Refactoring 1
    // Kent Beck 님 왈, 테스트에 있는 데이터와 코드의 중복도 제거하자.
    static class RefactoringEachTestRunner {
        private boolean wasRun; // 인스턴스 필드 추가
        
        public RefactoringEachTestRunner(Class<?> clazz, String methodName) {

        }

        public void run() {
            this.wasRun = true; // 인스턴스 필드에 값 할당
        }

        public boolean wasRun() {
            return this.wasRun; // 하드코딩된 값에서 인스턴스 필드를 반환하도록 변경
        }
    }

    // Red Test 3
    // 전달받은 클래스의 메서드가 없지만 wasRun에서 true를 반환하기 때문에 테스트 실패
    @Test
    void 전달받은_클래스의_메서드가_존재하지_않으면_실행하지_못한다_1() {
        // arrange
        RefactoringEachTestRunner runner = new RefactoringEachTestRunner(Dummy.class, "no_run");

        // act
        runner.run();

        /**
         * Expecting value to be false but was true
         * Expected :false
         * Actual   :true
         */
        // assert
        assertThat(runner.wasRun()).isFalse(); // assertThat(runner.wasRun()).isEqualTo(false);
    }

    static class OneMoreRefactoringEachTestRunner {
        private final Class<?> clazz;
        private final String methodName;
        private boolean wasRun;

        public OneMoreRefactoringEachTestRunner(Class<?> clazz, String methodName) {
            this.clazz = clazz;
            this.methodName = methodName;
        }

        public void run() {
            try {
                Object object = clazz.getDeclaredConstructor().newInstance();
                Method method = clazz.getDeclaredMethod(methodName);
                method.invoke(object);
                this.wasRun = true;
            } catch (Exception e) {
                this.wasRun = false;
            }
        }

        public boolean wasRun() {
            return this.wasRun;
        }
    }

    // Green Test 2
    @Test
    void 전달받은_클래스의_메서드가_존재하지_않으면_실행하지_못한다_2() {
        // arrange
        OneMoreRefactoringEachTestRunner runner = new OneMoreRefactoringEachTestRunner(Dummy.class, "no_run");

        // act
        runner.run();

        // assert
        assertThat(runner.wasRun()).isFalse(); // assertThat(runner.wasRun()).isEqualTo(false);
    }
}
