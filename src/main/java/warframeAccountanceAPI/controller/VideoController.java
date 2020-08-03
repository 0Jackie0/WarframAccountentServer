/**
 * 
 */
package warframeAccountanceAPI.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author user
 *
 */
@Slf4j
@RestController
@CrossOrigin
public class VideoController
{
	private final String WELCOME_VIDEO_PATH = "classpath:static/video/welcome_video_large.mp4";
//	private final String PATH_STRING = "C:\\Users\\l1290\\Desktop\\Work\\Spring Workspace\\WarframeAccountanceAPI\\src\\main\\resources\\static\\video\\welcome_video.mp4";
	
	@GetMapping("/api/video/respond")
	public void getVideoByRespondBody (HttpServletResponse response)
	{
		log.info("Get vedio by respond body");

		try 
		{
	        File file = ResourceUtils.getFile(WELCOME_VIDEO_PATH);
	        
	        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
	        response.setHeader("Content-Disposition", "attachment; filename="+file.getName().replace(" ", "_"));
	        
	        InputStream iStream = new FileInputStream(file);
	        IOUtils.copy(iStream, response.getOutputStream());
	        response.flushBuffer();
	    } 
		catch (java.nio.file.NoSuchFileException e) 
		{
			log.info(e.getMessage());
	        response.setStatus(HttpStatus.NOT_FOUND.value());
	    } 
		catch (Exception e) 
		{
			log.info(e.getMessage());
	        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	    }
	}

	
	@GetMapping("/api/video")
	public ResponseEntity<ResourceRegion> getVideoByRespondEntity (@RequestHeader HttpHeaders headers)
	{
		log.info("Get vedio by respond entity");

		UrlResource video = null;
		
		ResourceRegion region = null;
		
		try
		{
			video = new UrlResource(ResourceUtils.getURL(WELCOME_VIDEO_PATH));
			region = resourceRegion(video, headers);
		}
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return ResponseEntity.status(
				HttpStatus.PARTIAL_CONTENT).contentType(MediaTypeFactory.getMediaType(video).orElse(MediaType.APPLICATION_OCTET_STREAM)
						).body(region);
	}
	private ResourceRegion resourceRegion(UrlResource video, HttpHeaders headers) throws IOException
	{
		long contentLength = video.contentLength();
		List<HttpRange> rangeList = headers.getRange();
		
		long rangeLength = 0;
		if (rangeList.size() > 0) 
		{
			HttpRange range = headers.getRange().get(0);
			log.info("Has range " + range.getRangeStart(contentLength) + "=--=" + range.getRangeEnd(contentLength));
			
			long start = range.getRangeStart(contentLength);
					
			long end = range.getRangeEnd(contentLength);

			rangeLength = end - start + 1;//
//			if(2 * 1024 * 1024 > end - start + 1)
//			{
//				rangeLength = end - start + 1;
//				log.info("video length");
//			}
//			else
//			{
//				rangeLength = 2 * 1024 * 1024;
//				log.info("buffer length");
//			}
			
			return new ResourceRegion(video, start, rangeLength);
		}
		else 
		{
			log.info("No range");
			
//			if(1 * 1024 * 1024 > contentLength)
//			{
//				rangeLength = contentLength;
//			}
//			else
//			{
//				rangeLength = 1 * 1024 * 1024;
//			}
			
			return new ResourceRegion(video, 0, contentLength);
		}
	}

	
	@GetMapping("/api/video/android")
	@ResponseBody
	public FileSystemResource getVideoByFileSystemResource (HttpServletResponse response)
	{
		log.info("Get vedio by file resource");
		try
		{
			return new FileSystemResource(ResourceUtils.getFile(WELCOME_VIDEO_PATH).getPath());
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
