import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class HW1 {
	static SimpleDateFormat	inForm = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss(a)", new Locale("ko", "KR"));

	public static void main(String[] args) {
		String	path = args[0];  
		File	f = new File(path), dirs[];
		int		dir_num = 0;
		
		if(!f.exists()) {
			System.out.println(path+"의 디렉토리는 존재하지 않음!!");
			System.exit(0);
		}
		System.out.println(path+" 디렉토리의 내용");
		dirs = f.listFiles();
		ListDir(dirs,dir_num);
	}
	
	static void ListDir(File[] dirs, int dir_num) {
		File	cur;
		String	a = "\u27A5", blank = "     ";
	
		for(int i=0; i < dirs.length; i++) {
			cur = dirs[i];
			if (dir_num > 0) {
				for(int j=0; j< dir_num; j++) System.out.print(blank);
				System.out.print(a);
			}
			System.out.printf("%s%s[수정일:%s]\n", cur.getName(), 
					cur.isDirectory() ? "(디렉토리)" : "<"+cur.length()+" bytes>(파일)", inForm.format(cur.lastModified())
					);
			if(cur.isDirectory()) {
				dir_num +=1;
				ListDir(cur.listFiles(),dir_num);
			dir_num = 0;
			}
		}
		
	}

}
