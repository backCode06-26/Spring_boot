package hello.helloSpring.repository;

import hello.helloSpring.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository {

    private final DataSource dataSource;

    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Member save(Member member) {
        String sql = "insert into member(name) values(?)"; //쿼리문 이것보다 상수로 밖으로 빼는 것이 좋음

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null; // 결과을 받는 것

        try {
            conn = getConnection(); // h2db와 연결함
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // 쿼리을 넣을 준비함, 시퀀스

            pstmt.setString(1, member.getName());
            // 값을 저장하는 메서드, 첫번째 값은 인덱스(위의 메서드에서 시퀀스을 하여서 값이 올라감), 두번째는 값임

            pstmt.executeUpdate(); // 위의 쿼리을 h2db에 준다.
            rs = pstmt.getGeneratedKeys(); // key값을 얻는다.

            if (rs.next()) { // 값이 있는지 없는지
                member.setId(rs.getLong(1));
                // 값이 있으면, re.getLong(key의 값을 Long타입으로 바꾸는 메서드)을 사용하여, id을 저장한다.
            } else {
                throw new SQLException("id 조회 실패"); // 실패 메세지
            }

            return member; // return 값
        } catch (Exception e) {
            throw new IllegalStateException(e); // 오류 캐치

        } finally {
            close(conn, pstmt, rs); // 실패시 위의 변수 정지
        }
    }

    @Override
    public Optional<Member> findById(Long id) {
        String sql = "select * from member where id = ?"; //쿼리문

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql); // 쿼리문을 넣는것을 준비하는 단계이다.
            pstmt.setLong(1, id); // ?에 들어갈 순서을 정하는 단계

            rs = pstmt.executeQuery(); // 쿼리을 실행후 값을 가져오는 것

            if (rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id")); // 위에서 가져온 값에서 id, name 값의 저장함
                member.setName(rs.getString("name"));

                return Optional.of(member); //  null이 아닌 경우에서만 실행되며, null이면 오류가 생김
            } else {
                return Optional.empty(); // 값의 없을을 null대신으로 사용한다.
            }

        } catch (Exception e) {
            throw new IllegalStateException(e);

        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Member> findAll() {
        String sql = "select * from member"; //쿼리문

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            List<Member> members = new ArrayList<>();
            while (rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                members.add(member);
            }
            return members;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {

            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Member> findByName(String name) {
        String sql = "select * from member where name = ?"; //쿼리문

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    private Connection getConnection() {
        // Spring을 통해서 사용을 할때는 Utils을 통해서 해야하기 때문에 위와 같이 메서드 마다 사용한다.
        return DataSourceUtils.getConnection(dataSource);
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
