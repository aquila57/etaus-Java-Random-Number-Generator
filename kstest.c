/* kstest.c - KS Test, stdin input  Version 0.1.0 */
/* Copyright (C) 2019 aquila57 at github.com */

/* This program is free software; you can redistribute it and/or     */
/* modify it under the terms of the GNU General Public License as    */
/* published by the Free Software Foundation; either version 2 of    */
/* the License, or (at your option) any later version.               */

/* This program is distributed in the hope that it will be useful,   */
/* but WITHOUT ANY WARRANTY; without even the implied warranty of    */
/* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the      */
/* GNU General Public License for more details.                      */

/* You should have received a copy of the GNU General Public License */
/* along with this program; if not, write to:                        */

   /* Free Software Foundation, Inc.                                 */
   /* 59 Temple Place - Suite 330                                    */
   /* Boston, MA 02111-1307, USA.                                    */

#include "kstest.h"

double getetaus(xxfmt *xx)
   {
   int len;
   double num;
   char buf[256];
   len = scanf("%s", buf);
   if (len < 1)
      {
      if (feof(stdin))
	 {
	 xx->eofsw = 1;
         return(EOFKS);
	 } /* if end of file */
      if (ferror(stdin))
         {
         perror("getetaus: read error\n");
         exit(1);
         } /* read error */
      fprintf(stderr,"getetaus: blank line\n");
      exit(1);
      } /* if eof or read error */
   if (len > 1)
      {
      fprintf(stderr,"getetaus: read error\n");
      fprintf(stderr,"scanf read more than "
         "one token %d\n", len);
      exit(1);
      } /* invalid input */
   num = atof(buf);
   return(num);
   } /* getetaus */

int main(void)
   {
   xxfmt *xx;

   /*************************************************************/
   /* Allocate memory for the global structure.                 */
   /* This is a re-entrant program.                             */
   /*************************************************************/

   xx = (xxfmt *) malloc(sizeof(xxfmt));
   if (xx == NULL)
      {
      fprintf(stderr,"main: out of memory "
         "allocating xx\n");
      exit(1);
      } /* out of memory */

   /*************************************************************/
   /* initialize global data                                    */
   /*************************************************************/
   xx->eofsw = 0;
   xx->sumx = 0.0;
   xx->deltax = 1.0 / (double) SMPLS;
   xx->maxdiff = -999999999.0;
   xx->root = (treefmt *) treeinit();
   xx->dblsmpls = (double) SMPLS;
   xx->count = 0.0;
   xx->duplicates = 0.0;
   while (xx->count < xx->dblsmpls)
      {
      double num;
      num = getetaus(xx);
      isrt(xx->root, num, xx);
      xx->count += 1.0;
      } /* for each sample */
   if (xx->root->rght != NULL) traverse(xx->root->rght, xx);
   printf("Kolmogorov-Smirnov Test\n");
   printf("Java etaus Generator\n");
   printf("Maximum difference %f\n", xx->maxdiff);
   printf("Times sqrt(N)      %f\n",
      xx->maxdiff * sqrt(xx->dblsmpls));
   printf("Number of samples  %d\n", SMPLS);
   printf("Duplicate keys     %.0f\n", xx->duplicates);
   printf("\n");
   printf("Since number of samples is over 50,\n");
   printf("Probability    D-Max\n");
   printf("  0.001        1.94947\n");
   printf("  0.01         1.62762\n");
   printf("  0.02         1.51743\n");
   printf("  0.05         1.35810\n");
   if (xx->root->rght != NULL) rmtree(xx->root->rght);
   free(xx->root);     /* free root node in tree */
   free(xx);           /* free global structure */
   return(0);          /* end of job */
   } /* main */
