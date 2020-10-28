package dao;

import model.Word;
import utils.DBConection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordDAO implements IWordDAO {

    private List<Word> getWords(List<Word> listWord, Connection conn, PreparedStatement statement, ResultSet resultSet)
            throws SQLException {
        while (resultSet.next()) {
            Word word = new Word();
            word.setId(resultSet.getLong("id"));
            word.setHtml(resultSet.getString("html"));
            word.setDescription(resultSet.getString("description"));
            word.setPronounce(resultSet.getString("pronounce"));
            word.setWord(resultSet.getString("word"));
            listWord.add(word);
        }

        conn.close();
        statement.close();
        resultSet.close();

        return listWord;
    }

    @Override
    public List<Word> getAll() {
        List<Word> dictionary = new ArrayList<>();
        Connection conn = DBConection.getConnection();
        if (conn != null) {
            String sql = "SELECT * FROM av LIMIT 100";
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                return getWords(dictionary, conn, statement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override
    public Word findExactWord(String keyword) {
        Word word = new Word();
        Connection conn = DBConection.getConnection();
        if (conn != null) {
            String sql = "SELECT * FROM av WHERE UPPER(word) = ? LIMIT 1";
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, keyword);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    word.setId(resultSet.getLong("id"));
                    word.setHtml(resultSet.getString("html"));
                    word.setDescription(resultSet.getString("description"));
                    word.setPronounce(resultSet.getString("pronounce"));
                    word.setWord(resultSet.getString("word"));
                }

                conn.close();
                statement.close();
                resultSet.close();

                return word;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override
    public List<Word> findWordsNearMeaning(String keyword) {
        List<Word> listWord = new ArrayList<>();
        Connection conn = DBConection.getConnection();
        if (conn != null) {
            String sql = "SELECT * FROM av WHERE word LIKE ? ";
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, keyword);
                ResultSet resultSet = statement.executeQuery();
                return getWords(listWord, conn, statement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;

    }

    @Override
    public boolean deleteWordById(long id) {
        Connection conn = DBConection.getConnection();
        if (conn != null) {
            String sql = "DELETE FROM av WHERE id = ?";
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setLong(1, id);
                statement.executeUpdate();

                conn.close();
                statement.close();

                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean updateWord(Word word) {
        Connection conn = DBConection.getConnection();
        if (conn != null) {
            String sql = "UPDATE av SET html=? , description=?, pronounce=?, word=? WHERE id = ?";
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setLong(5, word.getId());
                statement.setString(1, word.getHtml());
                statement.setString(2, word.getDescription());
                statement.setString(3, word.getPronounce());
                statement.setString(4, word.getWord());
                statement.executeUpdate();

                conn.close();
                statement.close();

                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean addNewWord(Word word) {
        Connection conn = DBConection.getConnection();
        if (conn != null) {
            String sql = "INSERT INTO av (html, description, pronounce, word) VALUES (?, ?, ?, ?)";
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, word.getHtml());
                statement.setString(2, word.getDescription());
                statement.setString(3, word.getPronounce());
                statement.setString(4, word.getWord());
                statement.executeUpdate();

                conn.close();
                statement.close();

                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

}
