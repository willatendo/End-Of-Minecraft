package endofminecraft.server.event;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;

public class EndData {
	private File file;

	public EndData(String filename) {
		this.file = new File(filename);
	}

	public EndData() {
		this.file = null;
	}

	public void setFile(String filename) {
		this.file = new File(filename);
	}

	public boolean checkIfExists() {
		return this.file.exists();
	}

	public void createFile() {
		CompoundTag playerTags = new CompoundTag();
		CompoundTag spawnTag = new CompoundTag();
		CompoundTag wastelandData = new CompoundTag();
		wastelandData.put("PlayerTags", playerTags);
		wastelandData.put("SpawnTag", spawnTag);

		try {
			this.file.createNewFile();
			FileOutputStream e = new FileOutputStream(this.file);
			NbtIo.writeCompressed(wastelandData, e);
		} catch (IOException var5) {
			var5.printStackTrace();
		}

	}

	public List getPlayerNames() {
		ArrayList names = new ArrayList();

		try {
			FileInputStream e = new FileInputStream(this.file);
			CompoundTag wastelandData = NbtIo.readCompressed(e);
			names.addAll(wastelandData.getCompound("PlayerTags").getAllKeys());
		} catch (Exception var4) {
			var4.printStackTrace();
		}

		return names.isEmpty() ? null : names;
	}

	public void savePlayerNames(List names) {
		try {
			FileInputStream e = new FileInputStream(this.file);
			CompoundTag wastelandData = NbtIo.readCompressed(e);
			CompoundTag playerTags = wastelandData.getCompound("PlayerTags");

			for (int fileStreamOut = 0; fileStreamOut < names.size(); ++fileStreamOut) {
				playerTags.putString((String) names.get(fileStreamOut), "NA");
			}

			FileOutputStream var7 = new FileOutputStream(this.file);
			NbtIo.writeCompressed(wastelandData, var7);
		} catch (Exception var6) {
			var6.printStackTrace();
		}

	}

	public void savePlayerName(String name) {
		ArrayList names = new ArrayList();
		names.add(name);
		this.savePlayerNames(names);
	}

	public void saveSpawnLoc(BlockPos spawn) {
		try {
			FileInputStream e = new FileInputStream(this.file);
			CompoundTag wastelandData = NbtIo.readCompressed(e);
			CompoundTag spawnTag = wastelandData.getCompound("spawnTag");
			spawnTag.putInt("spawnX", spawn.getX());
			spawnTag.putInt("spawnY", spawn.getY());
			spawnTag.putInt("spawnZ", spawn.getZ());
			FileOutputStream fileStreamOut = new FileOutputStream(this.file);
			NbtIo.writeCompressed(wastelandData, fileStreamOut);
		} catch (Exception var6) {
			var6.printStackTrace();
		}

	}

	public BlockPos loadSpawnLoc() {
		BlockPos spawn = null;

		try {
			FileInputStream e = new FileInputStream(this.file);
			CompoundTag wastelandData = NbtIo.readCompressed(e);
			CompoundTag spawnTag = wastelandData.getCompound("spawnTag");
			spawn = new BlockPos(spawnTag.getInt("spawnX"), spawnTag.getInt("spawnY"), spawnTag.getInt("spawnZ"));
		} catch (Exception var5) {
			var5.printStackTrace();
		}

		return spawn;
	}
}
