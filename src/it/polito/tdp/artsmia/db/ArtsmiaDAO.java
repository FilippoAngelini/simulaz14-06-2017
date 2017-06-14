package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Exhibition;
import it.polito.tdp.artsmia.model.ExhibitionPair;

public class ArtsmiaDAO {

	public List<ArtObject> listObject() {
		
		String sql = "SELECT * from objects";

		List<ArtObject> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				result.add(new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Integer> listAnni() {
		
		String sql = "select distinct begin from exhibitions order by begin asc";

		List<Integer> result = new ArrayList<Integer>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				result.add(res.getInt("begin"));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Exhibition> getMostreDaAnno(Integer annoInizio) {
		
		String sql = "select * from exhibitions where begin>= ?";

		List<Exhibition> result = new ArrayList<Exhibition>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, annoInizio);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				Exhibition e = new Exhibition (res.getInt("exhibition_id"),res.getString("exhibition_department"), res.getString("exhibition_title"), res.getInt("begin"),res.getInt("end"));
				result.add(e);
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<ExhibitionPair> listAllCoppieExhibition(List<Exhibition> mostre, Integer annoInizio){
		String sql = "select e1.exhibition_id as id1, e2.exhibition_id as id2 from exhibitions e1, exhibitions e2 where e1.exhibition_id!=e2.exhibition_id AND e2.begin > e1.begin AND e1.end>=e2.begin AND e1.begin>=? AND e2.begin>=?";

		List<ExhibitionPair> result = new ArrayList<ExhibitionPair>();
		
		Map<Integer,Exhibition> map = new HashMap<Integer,Exhibition>();
		
		for(Exhibition e : mostre){
			map.put(e.getId(),e);
		}

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, annoInizio);
			st.setInt(2, annoInizio);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				ExhibitionPair ep = new ExhibitionPair (map.get(res.getInt("id1")),map.get(res.getInt("id2")));
				result.add(ep);
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void setOggetti(List<Exhibition> mostre) {
		String sql = "select * from exhibition_objects ";
		
		Map<Integer,Exhibition> map = new HashMap<Integer,Exhibition>();
		
		for(Exhibition e : mostre){
			map.put(e.getId(),e);
		}

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				if(map.containsKey(res.getInt("exhibition_id")))
					map.get(res.getInt("exhibition_id")).addOggetto(res.getInt("object_id"));
			}

			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
	}
}
