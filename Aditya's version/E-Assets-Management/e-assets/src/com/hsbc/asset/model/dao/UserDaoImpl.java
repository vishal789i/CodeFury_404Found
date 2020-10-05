package com.hsbc.asset.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hsbc.asset.exception.CategoryException;
import com.hsbc.asset.model.beans.Asset;
import com.hsbc.asset.model.beans.AssetType;
import com.hsbc.asset.utility.DBUtility;

public class UserDaoImpl implements UserDao {

	@Override
	public Asset storeAsset(Asset asset) throws  CategoryException{
		try {
			
			Connection connection = DBUtility.getConnection();
			String query = "insert into asset_details (asset_name, asset_type, asset_description, quantity) values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, asset.getAssetName());
			preparedStatement.setString(2, asset.getAssetType());
			preparedStatement.setString(3, asset.getAssetDescription());
			preparedStatement.setInt(4, asset.getQuantity());
			
			preparedStatement.executeUpdate();
			
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			asset.setAssetId(rs.getInt(1));
			
			
			preparedStatement.close();
			connection.close();
			return asset;
			
		}catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return asset;
	}

	@Override
	public AssetType addAssetType(AssetType assetType) {
		try {
			Connection connection = DBUtility.getConnection();
			String query = "Insert into asset_type values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, assetType.getTypeName());
			preparedStatement.setInt(2,assetType.getLendingPeriod());
			preparedStatement.setDouble(3, assetType.getFine());
			preparedStatement.setInt(4, assetType.getBan());
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
			return assetType;
			} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
	
			return assetType;
		
			}

	}
	
	

}
