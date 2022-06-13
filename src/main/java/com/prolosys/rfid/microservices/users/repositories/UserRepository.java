package com.prolosys.rfid.microservices.users.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prolosys.rfid.microservices.users.repositories.entities.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Serializable> {

	// Lista de todos los usuario sin el usuario root
	List<UserEntity> findAllByUsernameNotAndEnabled(@Param("username") String username, boolean enabled);
	
    public Boolean existsByEmail(String email);

    public Boolean existsByEmailAndIdNot(String email, Long id);
	
    public Boolean existsByUsername(String username);
	
    public Boolean existsByUsernameAndIdNot(String username, Long id);
	
	@Query("SELECT u.password FROM UserEntity u WHERE u.id = :id")
	String findPasswordById(@Param("id") Long id);
	
	@Query("SELECT u.password FROM UserEntity u WHERE u.username = :username")
	String findPasswordByUsername(@Param("username") String username);
	
	//Habilitar / Deshabilitar un usuario mediante el ID
	@Transactional
	@Modifying
	@Query("UPDATE UserEntity u set u.enabled =:enable WHERE u.id = :id")
	int enableById(@Param("id") Long id, @Param("enable") boolean enable);
	
	//Actualizar password de usuario mediante el username
	@Transactional
	@Modifying
	@Query("UPDATE UserEntity u set u.password =:password WHERE u.username = :username")
	int updatePasswordByUsername(@Param("username") String username, @Param("password") String password);
	
	@Transactional
	@Modifying
	@Query("UPDATE UserEntity u set u.password =:password WHERE u.id = :id")
	int updatePasswordById(@Param("id") Long id, @Param("password") String password);
	
	
	@Transactional
	@Modifying
	@Query("update UserEntity u set u.firstname = :firstname, u.lastname = :lastname, u.email = :email where u.id = :id")
	int update(@Param("id") int id, @Param("firstname") String firstname, @Param("lastname") String lastname, @Param("email") String email);

	@Transactional
	@Modifying
	@Query("update UserEntity u set u.password = :password where u.id = :id")
	int updatePassword(@Param("id") int id, @Param("password") String password);

	@Transactional
	@Modifying
	@Query("update UserEntity u set u.enabled = :enabled where u.id = :id")
	int update(@Param("id") int id, @Param("enabled") boolean enabled);
	
	@Transactional
	@Modifying
	@Query("update UserEntity u set u.passwordToken = :passwordToken where u.email = :email")
	int updatePasswordTokenByEmail(@Param("email") String email, @Param("passwordToken") String passwordToken);

	Optional<UserEntity> findById(int id);

    Optional<UserEntity> findByUsername(String username);
    
    Optional<UserEntity> findByEmailAndPasswordToken(String email, String passwordToken);
    
	/***************************************************/

    UserEntity findByEmail(String email);


//	@Query("SELECT new UserEntity(u.id, u.username, u.password, u.enabled) from UserEntity u WHERE u.username = :username")
//	public abstract Optional<UserEntity> findByUsernameforLogin(@Param(value = "username") String username);
//
//	@Query("SELECT new UserEntity(u.id, u.verified) from UserEntity u WHERE u.tokenVerification = :tokenVerification")
//	public abstract Optional<UserEntity> findByTokenVerification(
//			@Param(value = "tokenVerification") String tokenVerification);



//	public abstract Boolean existsByEmailAndTokenVerification(String email, String token);

    //	@Transactional
//	@Modifying
//	@Query("update UserEntity u set u.verified = true where u.id = :id")
//	public int verifyUserById(@Param("id") int id);
//

//	@Query("SELECT new RoleEntity(r.id, r.key) FROM RoleEntity r JOIN r.id UserEntity u  WHERE r.id = :id" )
//	public abstract Set<RoleEntity> findAllRolesByUserId(@Param(value = "id") int id);

    // SELECT c, p.name FROM Country c JOIN c.capital p

//	public abstract UserEntity findById(int id);
//
//	public List<UserEntity> findAllByOrderByNameAsc();
//
//	@Query(value = "SELECT * " + "FROM " + "users " + "INNER JOIN users_roles ON users.use_id = users_roles.use_id "
//			+ "INNER JOIN roles ON users_roles.rol_id = roles.rol_id " + "WHERE "
//			+ "roles.rol_name = 'ROLE_ADMIN_EPH' OR roles.rol_name = 'ROLE_CONSULTANT_PLS' OR users.use_id = ?1 "
//			+ "GROUP BY users.use_id " + "ORDER BY users.use_name ASC", nativeQuery = true)
//	public abstract List<UserEntity> findAllWithRolesAdminEphOrConsultantPlsOrUserId(int id);
//
//	@Query(value = "SELECT u.id, u.name FROM UserEntity u "
//			+ "ORDER BY u.name ASC")
//	public abstract List<Object[]> getAllWithIdAndName();

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u SET u.firstname = :firstname WHERE u.id = :id")
    int updateTitleOnly(@Param("id") int id, @Param("firstname") String firstname);

    //
    @Query(value = "SELECT new UserEntity(u.id, u.firstname) FROM UserEntity u WHERE u.firstname LIKE %:term% ORDER BY u.firstname ASC")
    List<UserEntity> searchByTerm(@Param("term") String term);

    @Query(value = "SELECT " + "	users.use_id, " + "	users.use_username, " + "	users.use_firstname, "
            + "	users.use_lastname, " + "	users.use_email, " + "	users.use_meetings_available, "
            + "	users.use_is_sponsor, " + "	users.create_date, " + "	users.use_verified, " + "	roles.rol_id, "
            + "	roles.rol_key, " + "	roles.rol_name " + "FROM " + "	users "
            + "INNER JOIN users_roles ON users_roles.use_id = users.use_id "
            + "INNER JOIN roles ON users_roles.rol_id = roles.rol_id", nativeQuery = true)
    List<Object[]> findAllUsersAndRoles();

}
