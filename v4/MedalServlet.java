import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/MedalServlet")
public class MedalServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pais = request.getParameter("pais");
        int oro = Integer.parseInt(request.getParameter("oro"));
        int plata = Integer.parseInt(request.getParameter("plata"));
        int bronce = Integer.parseInt(request.getParameter("bronce"));

        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "INSERT INTO medallas (pais, oro, plata, bronce) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, pais);
            stmt.setInt(2, oro);
            stmt.setInt(3, plata);
            stmt.setInt(4, bronce);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("index.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        List<Medal> medals = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM medallas";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                medals.add(new Medal(
                        rs.getString("pais"),
                        rs.getInt("oro"),
                        rs.getInt("plata"),
                        rs.getInt("bronce")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(medals);
        out.print(json);
        out.flush();
    }
}
