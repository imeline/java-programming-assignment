import java.io.File;

public class HW2 {
	public static void main(String[] args) {
		String	path = args[0]; 
		File	f = new File(path), dirs[];
		String	n1, n2;
		boolean	exist_dir=false;
		
		n1 = args[1]; 
		n2 = args[2];	
		
		if(!f.exists()) {
			System.out.println(path+"의 디렉토리를 찾을 수 없음 !!");
			System.exit(0);
		}
		dirs = f.listFiles();
		if(!ChangeNameDir(dirs, n1, n2,exist_dir)) System.out.printf("%s를 검색할 수 없음",n1);
	}
	
	static boolean ChangeNameDir(File[] dirs,String n1, String n2,boolean exist_dir) {
		File	cur;
	
		for(int i=0; i < dirs.length; i++) {
			cur = dirs[i];
			if(cur.isDirectory()) exist_dir = ChangeNameDir(cur.listFiles(), n1, n2, exist_dir);
			if(cur.getName().equals(n1)) {
				File	new_F = new File(cur.getParent()+"/"+n2);
				if (cur.renameTo(new_F)) {
					System.out.printf("%s를 %s로 변경함. [%s]\n",cur.getAbsolutePath(),new_F.getAbsolutePath(),new_F.isDirectory() ? "디렉토리" : "파일");
					exist_dir = true;
				}
			}
		}
		return exist_dir;
	}

}
