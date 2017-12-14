/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * Project Info:  http://plantuml.com
 * 
 * If you like this project or if you find it useful, you can support us at:
 * 
 * http://plantuml.com/patreon (only 1$ per month!)
 * http://plantuml.com/paypal
 * 
 * This file is part of Smetana.
 * Smetana is a partial translation of Graphviz/Dot sources from C to Java.
 *
 * (C) Copyright 2009-2017, Arnaud Roques
 *
 * This translation is distributed under the same Licence as the original C program.
 * 
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC
 * LICENSE ("AGREEMENT"). [Eclipse Public License - v 1.0]
 * 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES
 * RECIPIENT'S ACCEPTANCE OF THIS AGREEMENT.
 * 
 * You may obtain a copy of the License at
 * 
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package smetana.core;

import h.*;

import java.util.LinkedHashSet;
import java.util.Set;

import smetana.core.amiga.StarArrayOfInteger;
import smetana.core.amiga.StarArrayOfPtr;
import smetana.core.amiga.StarArrayOfStruct;
import smetana.core.amiga.StarStar;
import smetana.core.amiga.StarStruct;
import smetana.core.amiga.StarStructImpl;

// http://docs.oracle.com/javase/specs/jls/se5.0/html/expressions.html#15.7.4
// http://www.jbox.dk/sanos/source/lib/string.c.html

public class JUtils {

	public static int USHRT_MAX = 65535;

	public static size_t sizeof(Class cl) {
		return new size_t_struct(cl);
	}

	public static size_t sizeof(__ptr__ element) {
		return new size_t_of_element(element);
	}

	public static size_t sizeof(String name, int sz) {
		if (name.equals("char*")) {
			return new size_t_array_of_charstars(sz);
		}
		throw new UnsupportedOperationException();
	}

	public static size_t sizeof(Class cl, int nb) {
		if (cl == pointf.class) {
			return ST_pointf.sizeof(nb);
		}
		if (cl == boxf.class) {
			return ST_boxf.sizeof(nb);
		}
		if (cl == pointnlink_t.class) {
			return ST_pointnlink_t.sizeof(nb);
		}
		if (cl == textspan_t.class) {
			return ST_textspan_t.sizeof(nb);
		}
		// if (UmlDiagram.SMETANA_BETA) {
		if (cl == Agraph_s.class) {
			return ST_Agraph_s.sizeof(nb);
		}
		if (cl == rank_t.class) {
			return ST_rank_t.sizeof(nb);
		}
		if (cl == Agnode_s.class) {
			return ST_Agnode_s.sizeof(nb);
		}
		if (cl == bezier.class) {
			return ST_bezier.sizeof(nb);
		}
		if (cl == triangle_t.class) {
			return ST_triangle_t.sizeof(nb);
		}
		if (cl == Pedge_t.class) {
			return ST_Pedge_t.sizeof(nb);
		}
		if (cl == tna_t.class) {
			return ST_tna_t.sizeof(nb);
		}
		if (cl == object_t.class) {
			return ST_object_t.sizeof(nb);
		}
		if (cl == xlabel_t.class) {
			return ST_xlabel_t.sizeof(nb);
		}
		// }
		if (from(cl) instanceof __struct_impl__ == false) {
			throw new IllegalArgumentException(cl.getName());
		}
		return new size_t_array_of_something(cl, nb);
	}

	public static size_t size_t_array_of_integer(int nb) {
		return new size_t_array_of_integer(nb);
	}

	public static size_t sizeof_starstar_empty(Class cl, int nb) {
		if (cl == Agedge_s.class) {
			return ST_Agedge_s.sizeof_starstar_empty(nb);
		}
		if (cl == Agnode_s.class) {
			return ST_Agnode_s.sizeof_starstar_empty(nb);
		}
		if (cl == pointnlink_t.class) {
			return ST_pointnlink_t.sizeof_starstar_empty(nb);
		}
		if (from(cl) instanceof __struct_impl__ == false) {
			throw new IllegalArgumentException(cl.getName());
		}
		return new size_t_array_of_array_of_something_empty(cl, nb);
	}

	public static int strcmp(CString s1, CString s2) {
		return s1.compareTo(s2);
	}

	public static int strncmp(CString s1, CString s2, int n) {
		return s1.compareTo(s2, n);
	}

	public static CString strstr(CString s1, CString s2) {
		throw new UnsupportedOperationException("s1=" + s1 + " s2=" + s2);
	}

	public static void strncpy(CString destination, CString source, int nb) {
		destination.copyFrom(source, nb);
	}

	public static CString strchr(CString str, char c) {
		return str.strchr(c);
	}

	public static int strtol(CString str, CString[] endptr, int base) {
		if (base != 10) {
			throw new IllegalArgumentException();
		}
		CString end = str;
		int result = Integer.parseInt(end.getContent());
		endptr[0] = end.plus(("" + result).length());
		return result;
	}

	public static double strtod(CString str, CString[] endptr) {
		final double result = Double.parseDouble(str.getContent());
		return result;
	}

	public static double atof(CString str) {
		return Double.parseDouble(str.getContent());
	}

	public static int memcmp(__ptr__ s1, __ptr__ s2, int sz) {
		throw new UnsupportedOperationException("s1=" + s1 + " s2=" + s2 + " sz=" + sz);
	}

	public static void memset(__ptr__ obj, int value, size_t nbytes) {
		if (value != 0) {
			throw new UnsupportedOperationException();
		}
	}

	public static int strlen(CString s) {
		return s.length();
	}

	public static double abs(double x) {
		return Math.abs(x);
	}

	public static double cos(double x) {
		return Math.cos(x);
	}

	public static double sin(double x) {
		return Math.sin(x);
	}

	public static double sqrt(double x) {
		return Math.sqrt(x);
	}

	public static double atan2(double a, double b) {
		return Math.atan2(a, b);
	}

	public static double pow(double a, double b) {
		return Math.pow(a, b);
	}

	public static boolean isdigit(char c) {
		return Character.isDigit(c);
	}

	public static int atoi(CString s) {
		return Integer.parseInt(s.getContent());
	}

	public static char tolower(char c) {
		return Character.toLowerCase(c);
	}

	public static CFunction function(Class codingClass, String name) {
		return CFunctionImpl.create(codingClass, name);
	}

	public static int enumAsInt(Class enumClass, String name) {
		CEnumInterpretor interpretor = new CEnumInterpretor(enumClass);
		final int result = interpretor.valueOf(name);
		JUtils.LOG("result for " + name + " is " + result);
		return result;
	}

	public static CString getenv(CString var) {
		return null;
	}

	public static void LOG(String s) {
		// System.err.println(s);
	}

	public static void LOG2(String s) {
		// System.err.println(s);
	}

	public static boolean EQ(Object o1, Object o2) {
		if (o1 == o2) {
			return true;
		}
		if (o1 == null && o2 != null) {
			return false;
		}
		if (o2 == null && o1 != null) {
			return false;
		}
		// if (o1 instanceof AreaArray && o2 instanceof AreaArray) {
		// return ((AreaArray) o1).isSameThan((AreaArray) o2);
		// }
		if (o1 instanceof StarStruct && o2 instanceof StarStruct) {
			return ((StarStruct) o1).isSameThan((StarStruct) o2);
		}
		if (o1 instanceof CString && o2 instanceof CString) {
			return ((CString) o1).isSameThan((CString) o2);
		}
		if (o1 instanceof StarArrayOfPtr && o2 instanceof StarArrayOfPtr) {
			return ((StarArrayOfPtr) o1).isSameThan((StarArrayOfPtr) o2);
		}
		if (o1 instanceof StarStar && o2 instanceof StarStruct) {
			__ptr__ o1b = ((StarStar) o1).getPtr();
			if (((StarStruct) o1b).getRealClass() != ((StarStruct) o2).getRealClass()) {
				throw new UnsupportedOperationException();
			}
			return EQ(o1b, o2);
		}
		if (o1 instanceof StarStruct && o2 instanceof StarArrayOfPtr) {
			__ptr__ o2b = ((StarArrayOfPtr) o2).getPtrForEquality();
			if (((StarStruct) o1).getRealClass() != ((StarStruct) o2b).getRealClass()) {
				throw new UnsupportedOperationException();
			}
			return EQ(o1, o2b);
		}
		if (o1 instanceof StarStruct && o2 instanceof StarArrayOfStruct) {
			__ptr__ o2b = ((StarArrayOfStruct) o2).getPtrForEquality();
			if (((StarStruct) o1).getRealClass() != ((StarStruct) o2b).getRealClass()) {
				throw new UnsupportedOperationException();
			}
			return EQ(o1, o2b);
		}
		if (o1 instanceof StarStar && o2 instanceof StarArrayOfPtr) {
			__ptr__ o1b = ((StarStar) o1).getPtr();
			__ptr__ o2b = ((StarArrayOfPtr) o2).getPtr();
			if (((StarStruct) o1b).getRealClass() != ((StarStruct) o2b).getRealClass()) {
				throw new UnsupportedOperationException();
			}
			return EQ(o1b, o2b);
		}
		if (o1 instanceof StarArrayOfStruct && o2 instanceof StarArrayOfStruct) {
			StarArrayOfStruct oo1 = (StarArrayOfStruct) o1;
			StarArrayOfStruct oo2 = (StarArrayOfStruct) o2;
			return oo1.isSameThan(oo2);
		}
		if (o1 instanceof StarArrayOfPtr && o2 instanceof StarArrayOfStruct) {
			StarArrayOfPtr oo1 = (StarArrayOfPtr) o1;
			StarArrayOfStruct oo2 = (StarArrayOfStruct) o2;
			__struct__ s1 = oo1.getStruct();
			__struct__ s2 = oo2.getStruct();
			boolean result = s1.getInternalData().isSameThan(s2.getInternalData());
			return result;
		}
		System.err.println("o1=" + o1.getClass() + " " + o1);
		System.err.println("o2=" + o2.getClass() + " " + o2);
		throw new UnsupportedOperationException();
	}

	public static boolean NEQ(Object o1, Object o2) {
		return EQ(o1, o2) == false;
	}

	public static void qsort(__ptr__ array, int nb, size_t size, CFunction compare) {
		if (nb <= 1) {
			return;
		}
		JUtils.LOG("array=" + array);
		JUtils.LOG("nb=" + nb);
		JUtils.LOG("size=" + size);
		JUtils.LOG("compare=" + compare);
		boolean change;
		do {
			change = false;
			for (int i = 0; i < nb - 1; i++) {
				__ptr__ element1 = array.plus(i);
				__ptr__ element2 = array.plus(i + 1);
				Integer cmp = (Integer) compare.exe(element1, element2);
				JUtils.LOG("cmp=" + cmp);
				if (cmp.intValue() > 0) {
					change = true;
					if (array instanceof StarArrayOfInteger) {
						((StarArrayOfInteger) array).swap(i, i + 1);
					} else if (array instanceof STStarArrayOfPointer) {
						((STStarArrayOfPointer) array).swap(i, i + 1);
					} else {
						((StarStar) array).swap(i, i + 1);
					}
				}
			}
		} while (change);
		for (int i = 0; i < nb - 1; i++) {
			__ptr__ element1 = array.plus(i);
			__ptr__ element2 = array.plus(i + 1);
			JUtils.LOG("element1=" + element1);
			JUtils.LOG("element2=" + element2);
			Integer cmp = (Integer) compare.exe(element1, element2);
			JUtils.LOG("cmp=" + cmp);
			if (cmp.intValue() > 0) {
				throw new IllegalStateException();
			}
		}
	}

	static public int setjmp(jmp_buf jmp) {
		// if (jmp.hasBeenCalled()) {
		// throw new UnsupportedOperationException();
		// }
		jmp.saveCallingEnvironment();
		return 0;
	}

	// DEBUG

	public static void printDebugEdge(Agedge_s e) {
		System.err.println("*********** PRINT EDGE ********** ");
		final Agedgeinfo_t data = (Agedgeinfo_t) Macro.AGDATA(e).castTo(Agedgeinfo_t.class);
		final splines splines = (splines) data.getPtr("spl");
		__struct__<boxf> bb = splines.getStruct("bb");
		final bezier list = (bezier) splines.getPtr("list");
		System.err.println("splines.size=" + splines.getInt("size"));
		System.err.println("bb.LL=" + pointftoString(bb.getStruct("LL")));
		System.err.println("bb.UR=" + pointftoString(bb.getStruct("UR")));
		printDebugBezier((bezier) splines.getPtr("list").getPtr());

	}

	private static String pointftoString(__struct__<pointf> point) {
		final StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(point.getDouble("x"));
		sb.append(" ; ");
		sb.append(point.getDouble("y"));
		sb.append(")");
		return sb.toString();
	}

	private static void printDebugBezier(bezier bezier) {
		System.err.println("bezier.size=" + bezier.getInt("size"));
		System.err.println("bezier.sflag=" + bezier.getInt("sflag"));
		System.err.println("splines.eflag=" + bezier.getInt("eflag"));
		System.err.println("bezier.sp=" + pointftoString(bezier.getStruct("sp")));
		System.err.println("bezier.ep=" + pointftoString(bezier.getStruct("ep")));
		for (int i = 0; i < bezier.getInt("size"); i++) {
			final __ptr__ pt = bezier.getPtr("list").plus(i).getPtr();
			System.err.println("pt=" + pointftoString(pt));
		}
	}

	private static String pointftoString(__ptr__ point) {
		final StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(point.getDouble("x"));
		sb.append(" ; ");
		sb.append(point.getDouble("y"));
		sb.append(")");
		return sb.toString();
	}

	public static <C extends __ptr__> __struct__<C> from(Class<C> theClass) {
		if (theClass == _dtmethod_s.class) {
			return new ST_dtmethod_s();
		}
		if (theClass == _dtdisc_s.class) {
			return new ST_dtdisc_s();
		}
		if (theClass == Agdesc_s.class) {
			return new ST_Agdesc_s();
		}
		if (theClass == Agtag_s.class) {
			return new ST_Agtag_s();
		}
		if (theClass == Agiddisc_s.class) {
			return new ST_Agiddisc_s();
		}
		if (theClass == Agmemdisc_s.class) {
			return new ST_Agmemdisc_s();
		}
		if (theClass == nlist_t.class) {
			return new ST_nlist_t();
		}
		if (theClass == arrowname_t.class) {
			return new ST_arrowname_t();
		}
		if (theClass == arrowtype_t.class) {
			return new ST_arrowtype_t();
		}
		if (theClass == elist.class) {
			return new ST_elist();
		}
		if (theClass == pointf.class) {
			return new ST_pointf();
		}
		if (theClass == boxf.class) {
			return new ST_boxf();
		}
		if (theClass == port.class) {
			return new ST_port();
		}
		if (theClass == polygon_t.class) {
			return new ST_polygon_t();
		}
		if (theClass == shape_functions.class) {
			return new ST_shape_functions();
		}
		if (theClass == shape_desc.class) {
			return new ST_shape_desc();
		}
		if (theClass == deque_t.class) {
			return new ST_deque_t();
		}
		if (theClass == pointnlink_t.class) {
			return new ST_pointnlink_t();
		}
		if (theClass == Ppoly_t.class) {
			return new ST_Ppoly_t();
		}
		if (theClass == splineInfo.class) {
			return new ST_splineInfo();
		}
		if (theClass == textfont_t.class) {
			return new ST_textfont_t();
		}
		//
		// if (UmlDiagram.SMETANA_BETA) {
		if (theClass == Agsubnode_s.class) {
			return new ST_Agsubnode_s();
		}
		if (theClass == _dtlink_s.class) {
			return new ST_dtlink_s();
		}
		if (theClass == refstr_t.class) {
			return new ST_refstr_t();
		}
		if (theClass == Agsym_s.class) {
			return new ST_Agsym_s();
		}
		if (theClass == Agedge_s.class) {
			return new ST_Agedge_s();
		}
		if (theClass == Agobj_s.class) {
			return new ST_Agobj_s();
		}
		if (theClass == Agrec_s.class) {
			return new ST_Agrec_s();
		}
		if (theClass == Agraph_s.class) {
			return new ST_Agraph_s();
		}
		if (theClass == Agclos_s.class) {
			return new ST_Agclos_s();
		}
		if (theClass == Agdisc_s.class) {
			return new ST_Agdisc_s();
		}
		if (theClass == Agdstate_s.class) {
			return new ST_Agdstate_s();
		}
		if (theClass == Agiodisc_s.class) {
			return new ST_Agiodisc_s();
		}
		if (theClass == _dt_s.class) {
			return new ST_dt_s();
		}
		if (theClass == _dtdata_s.class) {
			return new ST_dtdata_s();
		}
		if (theClass == Agdatadict_s.class) {
			return new ST_Agdatadict_s();
		}
		if (theClass == Agattr_s.class) {
			return new ST_Agattr_s();
		}
		if (theClass == Agcbstack_s.class) {
			return new ST_Agcbstack_s();
		}
		if (theClass == Agnode_s.class) {
			return new ST_Agnode_s();
		}
		if (theClass == Agedgepair_s.class) {
			return new ST_Agedgepair_s();
		}
		if (theClass == Agraphinfo_t.class) {
			return new ST_Agraphinfo_t();
		}
		if (theClass == GVC_s.class) {
			return new ST_GVC_s();
		}
		if (theClass == GVCOMMON_t.class) {
			return new ST_GVCOMMON_t();
		}
		if (theClass == gvlayout_engine_s.class) {
			return new ST_gvlayout_engine_s();
		}
		if (theClass == gvlayout_features_t.class) {
			return new ST_gvlayout_features_t();
		}
		if (theClass == gvplugin_active_layout_t.class) {
			return new ST_gvplugin_active_layout_t();
		}
		if (theClass == gvplugin_installed_t.class) {
			return new ST_gvplugin_installed_t();
		}
		if (theClass == layout_t.class) {
			return new ST_layout_t();
		}
		if (theClass == Agnodeinfo_t.class) {
			return new ST_Agnodeinfo_t();
		}
		if (theClass == textlabel_t.class) {
			return new ST_textlabel_t();
		}
		if (theClass == textspan_t.class) {
			return new ST_textspan_t();
		}
		if (theClass == rank_t.class) {
			return new ST_rank_t();
		}
		if (theClass == adjmatrix_t.class) {
			return new ST_adjmatrix_t();
		}
		if (theClass == Agedgeinfo_t.class) {
			return new ST_Agedgeinfo_t();
		}
		if (theClass == splines.class) {
			return new ST_splines();
		}
		if (theClass == bezier.class) {
			return new ST_bezier();
		}
		if (theClass == _dthold_s.class) {
			return new ST_dthold_s();
		}
		//
		if (theClass == pack_info.class) {
			return new ST_pack_info();
		}
		if (theClass == aspect_t.class) {
			return new ST_aspect_t();
		}
		if (theClass == fontinfo.class) {
			return new ST_fontinfo();
		}
		if (theClass == IMapEntry_t.class) {
			return new ST_IMapEntry_t();
		}
		if (theClass == point.class) {
			return new ST_point();
		}
		if (theClass == nodequeue.class) {
			return new ST_nodequeue();
		}
		if (theClass == spline_info_t.class) {
			return new ST_spline_info_t();
		}
		if (theClass == path.class) {
			return new ST_path();
		}
		if (theClass == pathend_t.class) {
			return new ST_pathend_t();
		}
		if (theClass == inside_t.class) {
			return new ST_inside_t();
		}
		if (theClass == triangle_t.class) {
			return new ST_triangle_t();
		}
		if (theClass == tedge_t.class) {
			return new ST_tedge_t();
		}
		if (theClass == Pedge_t.class) {
			return new ST_Pedge_t();
		}
		if (theClass == tna_t.class) {
			return new ST_tna_t();
		}
		if (theClass == label_params_t.class) {
			return new ST_label_params_t();
		}
		if (theClass == object_t.class) {
			return new ST_object_t();
		}
		if (theClass == xlabel_t.class) {
			return new ST_xlabel_t();
		}
		if (theClass == XLabels_t.class) {
			return new ST_XLabels_t();
		}
		if (theClass == HDict_t.class) {
			return new ST_HDict_t();
		}
		if (theClass == RTree.class) {
			return new ST_RTree();
		}
		if (theClass == _Node_t___.class) {
			return new ST_Node_t___();
		}
		// }
		notFound(theClass);
		return new __struct_impl__<C>(theClass);
	}

	public static StarStruct create(Class theClass, StarStruct parent) {
		if (theClass == _dtmethod_s.class) {
			throw new IllegalArgumentException(theClass.toString());
		}
		if (theClass == _dtdisc_s.class) {
			return new ST_dtdisc_s(parent);
		}
		if (theClass == Agdesc_s.class) {
			return new ST_Agdesc_s(parent);
		}
		if (theClass == Agtag_s.class) {
			return new ST_Agtag_s(parent);
		}
		if (theClass == Agiddisc_s.class) {
			throw new IllegalArgumentException(theClass.toString());
		}
		if (theClass == Agmemdisc_s.class) {
			throw new IllegalArgumentException(theClass.toString());
		}
		if (theClass == nlist_t.class) {
			return new ST_nlist_t(parent);
		}
		if (theClass == arrowname_t.class) {
			throw new IllegalArgumentException(theClass.toString());
		}
		if (theClass == arrowtype_t.class) {
			throw new IllegalArgumentException(theClass.toString());
		}
		if (theClass == elist.class) {
			return new ST_elist(parent);
		}
		if (theClass == pointf.class) {
			return new ST_pointf(parent);
		}
		if (theClass == boxf.class) {
			return new ST_boxf(parent);
		}
		if (theClass == port.class) {
			return new ST_port(parent);
		}
		if (theClass == polygon_t.class) {
			return new ST_polygon_t(parent);
		}
		if (theClass == shape_functions.class) {
			return new ST_shape_functions(parent);
		}
		if (theClass == shape_desc.class) {
			return new ST_shape_desc(parent);
		}
		if (theClass == deque_t.class) {
			return new ST_deque_t(parent);
		}
		if (theClass == pointnlink_t.class) {
			return new ST_pointnlink_t(parent);
		}
		if (theClass == Ppoly_t.class) {
			return new ST_Ppoly_t(parent);
		}
		if (theClass == splineInfo.class) {
			return new ST_splineInfo(parent);
		}
		if (theClass == textfont_t.class) {
			return new ST_textfont_t(parent);
		}
		//
		// if (UmlDiagram.SMETANA_BETA) {
		if (theClass == Agsubnode_s.class) {
			return new ST_Agsubnode_s(parent);
		}
		if (theClass == _dtlink_s.class) {
			return new ST_dtlink_s(parent);
		}
		if (theClass == refstr_t.class) {
			return new ST_refstr_t(parent);
		}
		if (theClass == Agsym_s.class) {
			return new ST_Agsym_s(parent);
		}
		if (theClass == Agedge_s.class) {
			return new ST_Agedge_s(parent);
		}
		if (theClass == Agobj_s.class) {
			return new ST_Agobj_s(parent);
		}
		if (theClass == Agrec_s.class) {
			return new ST_Agrec_s(parent);
		}
		if (theClass == Agraph_s.class) {
			return new ST_Agraph_s(parent);
		}
		if (theClass == Agclos_s.class) {
			return new ST_Agclos_s(parent);
		}
		if (theClass == Agdisc_s.class) {
			return new ST_Agdisc_s(parent);
		}
		if (theClass == Agdstate_s.class) {
			return new ST_Agdstate_s(parent);
		}
		if (theClass == _dt_s.class) {
			return new ST_dt_s(parent);
		}
		if (theClass == _dtdata_s.class) {
			return new ST_dtdata_s(parent);
		}
		if (theClass == Agdatadict_s.class) {
			return new ST_Agdatadict_s(parent);
		}
		if (theClass == Agattr_s.class) {
			return new ST_Agattr_s(parent);
		}
		if (theClass == Agcbstack_s.class) {
			return new ST_Agcbstack_s(parent);
		}
		if (theClass == Agnode_s.class) {
			return new ST_Agnode_s(parent);
		}
		if (theClass == Agedgepair_s.class) {
			return new ST_Agedgepair_s(parent);
		}
		if (theClass == Agraphinfo_t.class) {
			return new ST_Agraphinfo_t(parent);
		}
		if (theClass == GVC_s.class) {
			return new ST_GVC_s(parent);
		}
		if (theClass == GVCOMMON_t.class) {
			return new ST_GVCOMMON_t(parent);
		}
		if (theClass == gvlayout_engine_s.class) {
			throw new UnsupportedOperationException();
			// return new ST_gvlayout_engine_s(parent);
		}
		if (theClass == gvlayout_features_t.class) {
			throw new UnsupportedOperationException();
			// return new ST_gvlayout_features_t(parent);
		}
		if (theClass == gvplugin_active_layout_t.class) {
			throw new UnsupportedOperationException();
			// return new ST_gvplugin_active_layout_t(parent);
		}
		if (theClass == gvplugin_installed_t.class) {
			return new ST_gvplugin_installed_t(parent);
		}
		if (theClass == layout_t.class) {
			return new ST_layout_t(parent);
		}
		if (theClass == Agnodeinfo_t.class) {
			return new ST_Agnodeinfo_t(parent);
		}
		if (theClass == textlabel_t.class) {
			return new ST_textlabel_t(parent);
		}
		if (theClass == textspan_t.class) {
			return new ST_textspan_t(parent);
		}
		if (theClass == rank_t.class) {
			return new ST_rank_t(parent);
		}
		if (theClass == adjmatrix_t.class) {
			return new ST_adjmatrix_t(parent);
		}
		if (theClass == Agedgeinfo_t.class) {
			return new ST_Agedgeinfo_t(parent);
		}
		if (theClass == splines.class) {
			return new ST_splines(parent);
		}
		if (theClass == bezier.class) {
			return new ST_bezier(parent);
		}
		if (theClass == _dthold_s.class) {
			return new ST_dthold_s(parent);
		}
		//
		if (theClass == pack_info.class) {
			return new ST_pack_info(parent);
		}
		if (theClass == aspect_t.class) {
			return new ST_aspect_t(parent);
		}
		if (theClass == fontinfo.class) {
			return new ST_fontinfo(parent);
		}
		if (theClass == IMapEntry_t.class) {
			return new ST_IMapEntry_t(parent);
		}
		if (theClass == point.class) {
			return new ST_point(parent);
		}
		if (theClass == nodequeue.class) {
			return new ST_nodequeue(parent);
		}
		if (theClass == spline_info_t.class) {
			return new ST_spline_info_t(parent);
		}
		if (theClass == path.class) {
			return new ST_path(parent);
		}
		if (theClass == pathend_t.class) {
			return new ST_pathend_t(parent);
		}
		if (theClass == inside_t.class) {
			return new ST_inside_t(parent);
		}
		if (theClass == triangle_t.class) {
			return new ST_triangle_t(parent);
		}
		if (theClass == tedge_t.class) {
			return new ST_tedge_t(parent);
		}
		if (theClass == Pedge_t.class) {
			return new ST_Pedge_t(parent);
		}
		if (theClass == tna_t.class) {
			return new ST_tna_t(parent);
		}
		if (theClass == label_params_t.class) {
			return new ST_label_params_t(parent);
		}
		if (theClass == object_t.class) {
			return new ST_object_t(parent);
		}
		if (theClass == xlabel_t.class) {
			return new ST_xlabel_t(parent);
		}
		if (theClass == XLabels_t.class) {
			return new ST_XLabels_t(parent);
		}
		if (theClass == HDict_t.class) {
			return new ST_HDict_t(parent);
		}
		if (theClass == RTree.class) {
			return new ST_RTree(parent);
		}
		if (theClass == _Node_t___.class) {
			return new ST_Node_t___(parent);
		}
		// }
		notFound(theClass);
		return new StarStructImpl(theClass, parent);
	}

	private static final Set<String> todo = new LinkedHashSet<String>();

	private static void notFound(Class theClass) {
		todo.add(theClass.getName());
		System.err.println("todo=" + todo);
	}

}
