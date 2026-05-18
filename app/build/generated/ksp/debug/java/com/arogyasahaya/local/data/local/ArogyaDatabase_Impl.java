package com.arogyasahaya.local.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.arogyasahaya.local.data.local.dao.HealthCampDao;
import com.arogyasahaya.local.data.local.dao.HealthCampDao_Impl;
import com.arogyasahaya.local.data.local.dao.MedicineDao;
import com.arogyasahaya.local.data.local.dao.MedicineDao_Impl;
import com.arogyasahaya.local.data.local.dao.VitalLogDao;
import com.arogyasahaya.local.data.local.dao.VitalLogDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ArogyaDatabase_Impl extends ArogyaDatabase {
  private volatile MedicineDao _medicineDao;

  private volatile VitalLogDao _vitalLogDao;

  private volatile HealthCampDao _healthCampDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `medicines` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `dosage` TEXT NOT NULL, `times_of_day` TEXT NOT NULL, `start_date` INTEGER NOT NULL, `is_active` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `vital_logs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `date` INTEGER NOT NULL, `blood_pressure_systolic` INTEGER, `blood_pressure_diastolic` INTEGER, `heart_rate` INTEGER, `glucose_level` REAL, `notes` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `health_camps` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `camp_name` TEXT NOT NULL, `location` TEXT NOT NULL, `date` INTEGER NOT NULL, `asha_worker_name` TEXT NOT NULL, `description` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '24a6d77fbd6fe1832c7ce522b2cfb9d1')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `medicines`");
        db.execSQL("DROP TABLE IF EXISTS `vital_logs`");
        db.execSQL("DROP TABLE IF EXISTS `health_camps`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsMedicines = new HashMap<String, TableInfo.Column>(6);
        _columnsMedicines.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedicines.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedicines.put("dosage", new TableInfo.Column("dosage", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedicines.put("times_of_day", new TableInfo.Column("times_of_day", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedicines.put("start_date", new TableInfo.Column("start_date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedicines.put("is_active", new TableInfo.Column("is_active", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMedicines = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMedicines = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMedicines = new TableInfo("medicines", _columnsMedicines, _foreignKeysMedicines, _indicesMedicines);
        final TableInfo _existingMedicines = TableInfo.read(db, "medicines");
        if (!_infoMedicines.equals(_existingMedicines)) {
          return new RoomOpenHelper.ValidationResult(false, "medicines(com.arogyasahaya.local.data.local.entity.MedicineEntity).\n"
                  + " Expected:\n" + _infoMedicines + "\n"
                  + " Found:\n" + _existingMedicines);
        }
        final HashMap<String, TableInfo.Column> _columnsVitalLogs = new HashMap<String, TableInfo.Column>(7);
        _columnsVitalLogs.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVitalLogs.put("date", new TableInfo.Column("date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVitalLogs.put("blood_pressure_systolic", new TableInfo.Column("blood_pressure_systolic", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVitalLogs.put("blood_pressure_diastolic", new TableInfo.Column("blood_pressure_diastolic", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVitalLogs.put("heart_rate", new TableInfo.Column("heart_rate", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVitalLogs.put("glucose_level", new TableInfo.Column("glucose_level", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVitalLogs.put("notes", new TableInfo.Column("notes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVitalLogs = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesVitalLogs = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoVitalLogs = new TableInfo("vital_logs", _columnsVitalLogs, _foreignKeysVitalLogs, _indicesVitalLogs);
        final TableInfo _existingVitalLogs = TableInfo.read(db, "vital_logs");
        if (!_infoVitalLogs.equals(_existingVitalLogs)) {
          return new RoomOpenHelper.ValidationResult(false, "vital_logs(com.arogyasahaya.local.data.local.entity.VitalLogEntity).\n"
                  + " Expected:\n" + _infoVitalLogs + "\n"
                  + " Found:\n" + _existingVitalLogs);
        }
        final HashMap<String, TableInfo.Column> _columnsHealthCamps = new HashMap<String, TableInfo.Column>(6);
        _columnsHealthCamps.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHealthCamps.put("camp_name", new TableInfo.Column("camp_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHealthCamps.put("location", new TableInfo.Column("location", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHealthCamps.put("date", new TableInfo.Column("date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHealthCamps.put("asha_worker_name", new TableInfo.Column("asha_worker_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHealthCamps.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysHealthCamps = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesHealthCamps = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoHealthCamps = new TableInfo("health_camps", _columnsHealthCamps, _foreignKeysHealthCamps, _indicesHealthCamps);
        final TableInfo _existingHealthCamps = TableInfo.read(db, "health_camps");
        if (!_infoHealthCamps.equals(_existingHealthCamps)) {
          return new RoomOpenHelper.ValidationResult(false, "health_camps(com.arogyasahaya.local.data.local.entity.HealthCampEntity).\n"
                  + " Expected:\n" + _infoHealthCamps + "\n"
                  + " Found:\n" + _existingHealthCamps);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "24a6d77fbd6fe1832c7ce522b2cfb9d1", "a5dd98cc747196d54ccb6a675a76a72f");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "medicines","vital_logs","health_camps");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `medicines`");
      _db.execSQL("DELETE FROM `vital_logs`");
      _db.execSQL("DELETE FROM `health_camps`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(MedicineDao.class, MedicineDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(VitalLogDao.class, VitalLogDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(HealthCampDao.class, HealthCampDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public MedicineDao medicineDao() {
    if (_medicineDao != null) {
      return _medicineDao;
    } else {
      synchronized(this) {
        if(_medicineDao == null) {
          _medicineDao = new MedicineDao_Impl(this);
        }
        return _medicineDao;
      }
    }
  }

  @Override
  public VitalLogDao vitalLogDao() {
    if (_vitalLogDao != null) {
      return _vitalLogDao;
    } else {
      synchronized(this) {
        if(_vitalLogDao == null) {
          _vitalLogDao = new VitalLogDao_Impl(this);
        }
        return _vitalLogDao;
      }
    }
  }

  @Override
  public HealthCampDao healthCampDao() {
    if (_healthCampDao != null) {
      return _healthCampDao;
    } else {
      synchronized(this) {
        if(_healthCampDao == null) {
          _healthCampDao = new HealthCampDao_Impl(this);
        }
        return _healthCampDao;
      }
    }
  }
}
