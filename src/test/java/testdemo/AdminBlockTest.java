package testdemo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AdminBlockTest {
    @Test
    @DisplayName("새로 생성한 관리자는 차단 상태")
    void newCreatedAdmin_NonBlock() {
        final Admin admin = new Admin();
        assertThat(admin.isBlocked()).isFalse();
    }

    @Test
    void block() {
        Admin admin = new Admin();
        admin.block();
        assertThat(admin.isBlocked()).isTrue();
    }

    @Test
    void alreadyBlocked() {
        Admin admin = new Admin();

        admin.block();

        assertThatThrownBy(() -> {
            admin.block();
        }).isInstanceOf(AlreadyBlockedException.class);
    }

    @Test
    void unblock_WhenNotBlocked() {
        Admin admin = new Admin();

        assertThatThrownBy(() -> {
            admin.unblock();
        }).isInstanceOf(NonBlockedException.class);
    }

    @Test
    void unblock() {
        Admin admin = new Admin();

        admin.block();

        admin.unblock();

        assertThat(admin.isBlocked()).isFalse();
    }

    private class Admin {
        private boolean blocked = false;

        public boolean isBlocked() {
            return blocked;
        }

        public void block() {
            if (blocked) {
                throw new AlreadyBlockedException();
            }

            blocked = true;
        }

        public void unblock() {
            if (!blocked) {
                throw new NonBlockedException();
            }

            blocked = false;
        }
    }
}
